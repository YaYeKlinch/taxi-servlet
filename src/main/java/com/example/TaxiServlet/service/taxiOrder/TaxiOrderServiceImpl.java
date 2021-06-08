package com.example.TaxiServlet.service.taxiOrder;

import com.example.TaxiServlet.dao.DaoFactory;
import com.example.TaxiServlet.dao.taxiOrder.TaxiOrderDao;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.entity.dto.StatisticDto;
import com.example.TaxiServlet.entity.dto.TaxiOrderDto;

import java.time.LocalDateTime;
import java.util.List;

public class TaxiOrderServiceImpl implements TaxiOrderService{
    private  DaoFactory daoFactory = DaoFactory.getInstance();
    @Override
    public boolean createTaxiOrder(TaxiOrderDto taxiOrderDto, long userId , long carId) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            double distance = taxiOrderDto.calculateDistance();
            TaxiOrder taxiOrder = new TaxiOrder();
            taxiOrder.setTime(LocalDateTime.now());
            taxiOrder.setDeparture(taxiOrderDto.getDeparture());
            taxiOrder.setArrival(taxiOrderDto.getArrival());
            taxiOrder.setDistance(distance);
            taxiOrder.setCosts(taxiOrderDto.calculateCosts(distance));
            taxiOrder.setUserId(userId);
            taxiOrder.setCarId(carId);
            return taxiOrderDao.create(taxiOrder);
        }
    }

    @Override
    public List<OrderCarUserDto> getAllTaxiOrders(int count, int size ,String filter) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.getOrderCarUserNotSortedList(count , size, filter);
        }
    }

    @Override
    public List<OrderCarUserDto> getAllTaxiOrdersByUser(long user_id , int count , int size) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.getOrderCarUserListByUser(user_id , count , size);
        }
    }

    @Override
    public long getNumberOfOrders() {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.count();
        }
    }

    @Override
    public List<OrderCarUserDto> getAllTaxiOrderSortedByTime(int count, int size,String sort ,String filter) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            if("DESC".equals(sort)){
                return  taxiOrderDao.getOrderCarUserListSortedByDataDesc(count,size, filter);
            }
          return  taxiOrderDao.getOrderCarUserListSortedByDataAsc(count,size, filter);
        }
    }

    @Override
    public List<OrderCarUserDto> getAllTaxiOrderSortedByCosts(int count, int size, String sort,String filter) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            if("DESC".equals(sort)){
                return  taxiOrderDao.getOrderCarUserListSortedByCostsDesc(count,size,filter);
            }
            return  taxiOrderDao.getOrderCarUserListSortedByCostsAsc(count,size,filter);
        }
    }

    @Override
    public long getNumberOfOrdersByUser(long userId) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.getOrdersCountByUser(userId);
        }
    }

    @Override
    public List<StatisticDto> getStatistics(int count, int size) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.getTotalCostsAndDistance(count,size);
        }
    }

    @Override
    public long getCarsCountInOrder() {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.getCarCountInOrders();
        }
    }
}
