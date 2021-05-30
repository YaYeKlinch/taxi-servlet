package com.example.TaxiServlet.dao;


import com.example.TaxiServlet.dao.functional.PreparedStatementAction;
import com.example.TaxiServlet.dao.functional.PreparedStatementAndEntityAction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class JDBCDao<E> implements GenericDao<E> {

    protected Connection connection;
    protected String CreateQuery;
    protected String FindByIDQuery;
    protected String FindRangeQuery;
    protected String FindAllQuery;
    protected String CountQuery;
    protected String CountColumnLabel;
    protected String UpdateQuery;
    protected int UpdateIdParameterIndex;
    protected String DeleteQuery;
    protected Mapper<E> mapper;
    public JDBCDao(Connection connection, String createQuery, String findByIDQuery, String findRangeQuery, String findAllQuery, String countQuery, String countColumnLabel, String updateQuery, int updateIdParameterIndex, String deleteQuery, Mapper<E> mapper) {
        this.connection = connection;
        CreateQuery = createQuery;
        FindByIDQuery = findByIDQuery;
        FindRangeQuery = findRangeQuery;
        FindAllQuery = findAllQuery;
        CountQuery = countQuery;
        CountColumnLabel = countColumnLabel;
        UpdateQuery = updateQuery;
        UpdateIdParameterIndex = updateIdParameterIndex;
        DeleteQuery = deleteQuery;
        this.mapper = mapper;
    }

    @Override
    public boolean create(E entity) {
        boolean created = false;
        try (PreparedStatement statement = connection.prepareStatement(CreateQuery, Statement.RETURN_GENERATED_KEYS)) {
            created = transaction(statement,entity,this::createAction);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return created;
    }

    @Override
    public Optional<E> findById(long id) {
        E entity = null;

        try (PreparedStatement statement = connection.prepareStatement(FindByIDQuery)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                entity = extractEntity(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public List<E> findRange(int start, int count){
        List<E> found = null;
        try (PreparedStatement statement = connection.prepareStatement(FindRangeQuery)){
            statement.setInt(1,start);
            statement.setInt(2,count);
            found = getAllFromStatement(statement);
        }catch (Exception ex){
            ex.printStackTrace();
            found = new ArrayList<>();
        }
        return found;
    }


    @Override
    public List<E> findAll() {
        List<E> found = null;
        try (PreparedStatement statement = connection.prepareStatement(FindAllQuery)){
            found = getAllFromStatement(statement);
        }catch (Exception ex){
            ex.printStackTrace();
            found = new ArrayList<>();
        }
        return found;
    }


    @Override
    public long count() {
        return count(CountQuery,CountColumnLabel);
    }

    @Override
    public boolean update(E entity) {
        boolean created = false;
        try (PreparedStatement statement = connection.prepareStatement(UpdateQuery, Statement.RETURN_GENERATED_KEYS)) {
            created = transaction(statement,entity,this::updateAction);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return created;
    }


    @Override
    public boolean delete(long id) {
        boolean affected = false;
        try (PreparedStatement statement = connection.prepareStatement(DeleteQuery)) {
            statement.setLong(1,id);
            affected = transaction(statement,this::deleteEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return affected;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean transaction(PreparedStatement statement, PreparedStatementAction action)throws SQLException{
        boolean success = false;
        boolean wasAutocommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try{
            if(action.execute(statement)){
                success = true;
            }else{
                connection.rollback();
            }
        }catch (Exception ex){
            connection.rollback();
            ex.printStackTrace();
        }
        if(wasAutocommit){
            connection.commit();
            connection.setAutoCommit(false);
        }
        return success;
    }

    <T> boolean transaction(PreparedStatement statement, T entity, PreparedStatementAndEntityAction<T> action)throws SQLException{
        boolean success = false;
        boolean wasAutocommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try{
            if(action.execute(statement,entity)){
                success = true;
            }else{
                connection.rollback();
            }
        }catch (Exception ex){
            connection.rollback();
            ex.printStackTrace();
        }
        if(wasAutocommit){
            connection.commit();
            connection.setAutoCommit(false);
        }
        return success;
    }

    boolean createAction(PreparedStatement statement,E entity) throws SQLException{
        if (insertIntoDb(statement,entity) == 1) {
            setId(entity, getId(entity, statement));
            return true;
        }
        return false;
    }

    boolean updateAction(PreparedStatement statement, E entity) throws SQLException{
        return updateOnDb(statement,entity) == 1;
    }


    long getId(E entity, Statement statement) throws SQLException {

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getLong(1);
        } else {
            throw new IllegalArgumentException("generatedKeys is empty");
        }
    }

    protected List<E> getAllFromStatement(PreparedStatement statement) throws SQLException {
        List<E> entities = new ArrayList<>();
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            entities.add(extractEntity(rs));
        }
        return entities;
    }

    int insertIntoDb(PreparedStatement statement, E entity) throws SQLException {
        setEntityValues(statement, entity);
        return statement.executeUpdate();
    }

    int updateOnDb(PreparedStatement statement, E entity) throws SQLException {
        setEntityValues(statement, entity);
        statement.setLong(UpdateIdParameterIndex, getId(entity));
        return statement.executeUpdate();
    }

    protected E extractEntity(ResultSet rs) throws SQLException {
        return mapper.extractFromResultSet(rs);
    }

    boolean deleteEntity(PreparedStatement statement) throws SQLException {
        return statement.executeUpdate() > 0;
    }

    long count(String query, String countColumnLabel) {
        int count = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt(countColumnLabel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    protected abstract long getId(E entity);

    protected abstract void setId(E entity, long Id) throws SQLException;

    protected abstract void setEntityValues(PreparedStatement statement, E entity) throws SQLException;
}