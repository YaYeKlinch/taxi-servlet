package com.example.TaxiServlet.service.taxiOrder;

import com.example.TaxiServlet.dao.DaoFactory;
import com.example.TaxiServlet.dao.taxiOrder.TaxiOrderDao;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.entity.dto.TaxiOrderDto;

import java.time.LocalDateTime;
import java.util.List;

public class TaxiOrderServiceImpl implements TaxiOrderService{
    private final DaoFactory daoFactory = DaoFactory.getInstance();
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
    public List<OrderCarUserDto> getAllTaxiOrders(int count, int size) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.getOrderCarUserNotSortedList(count , size);
        }
    }

    @Override
    public List<OrderCarUserDto> getAllTaxiOrdersByUser(long user_id) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.getOrderCarUserListByUser(user_id);
        }
    }

    @Override
    public long getNumberOfOrders() {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            return  taxiOrderDao.count();
        }
    }

    @Override
    public List<OrderCarUserDto> getAllTaxiOrderSortedByTime(int count, int size,String sort) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            if("DESC".equals(sort)){
                return  taxiOrderDao.getOrderCarUserListSortedByDataDesc(count,size);
            }
          return  taxiOrderDao.getOrderCarUserListSortedByDataAsc(count,size);
        }
    }

    @Override
    public List<OrderCarUserDto> getAllTaxiOrderSortedByCosts(int count, int size, String sort) {
        try (TaxiOrderDao taxiOrderDao = daoFactory.createTaxiOrderDao()) {
            if("DESC".equals(sort)){
                return  taxiOrderDao.getOrderCarUserListSortedByCostsDesc(count,size);
            }
            return  taxiOrderDao.getOrderCarUserListSortedByCostsAsc(count,size);
        }
    }
}
