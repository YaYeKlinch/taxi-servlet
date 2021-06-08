package com.example.TaxiServlet.service.taxiOrder;

import com.example.TaxiServlet.dao.DaoFactory;
import com.example.TaxiServlet.dao.taxiOrder.TaxiOrderDao;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.entity.dto.StatisticDto;
import com.example.TaxiServlet.entity.dto.TaxiOrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxiOrderServiceImplTest {

    @Mock
    DaoFactory factory;

    @Mock
    TaxiOrderDao dao;

    @Mock
    TaxiOrder taxiOrder;

    @Mock
    List<TaxiOrder> taxiOrders;

    @Mock
    List<OrderCarUserDto> orderCarUserDtoList;

    @Mock
    List<StatisticDto> statisticDtoList;

    @InjectMocks
    TaxiOrderServiceImpl taxiOrderService;

    private static final int COUNT_PAGE = 1;
    private static final int SIZE_PAGE = 1;
    private static final String FILTER = "filter";
    private static final int USER_ID = 1;
    private static final int CAR_ID = 1;
    private static final String SORT_DESC = "DESC";
    private static final String SORT_ASC = "ASC";
    private static final long COUNT = 1;
    private static final String DEPARTURE = "department";
    private static final String ARRIVAL = "arrival";


    @BeforeEach
    void init() {
        when(factory.createTaxiOrderDao()).thenReturn(dao);
    }

    @Test
    void shouldReturnAllOrderCarUserDto(){
        when(dao.getOrderCarUserNotSortedList(COUNT_PAGE, SIZE_PAGE,FILTER)).thenReturn(orderCarUserDtoList);
        assertSame(orderCarUserDtoList , taxiOrderService.getAllTaxiOrders(COUNT_PAGE, SIZE_PAGE,FILTER));
    }

    @Test
    void shouldReturnAllOrderCarUserDtoByUser(){
        when(dao.getOrderCarUserListByUser(USER_ID, COUNT_PAGE, SIZE_PAGE)).thenReturn(orderCarUserDtoList);
        assertSame(orderCarUserDtoList , taxiOrderService.getAllTaxiOrdersByUser(USER_ID, COUNT_PAGE, SIZE_PAGE));
    }

    @Test
    void shouldReturnAllOrderCarUserDto_WhenSortedByCostsDecs(){
        when(dao.getOrderCarUserListSortedByCostsDesc(COUNT_PAGE, SIZE_PAGE,FILTER)).thenReturn(orderCarUserDtoList);
        assertSame(orderCarUserDtoList , taxiOrderService.getAllTaxiOrderSortedByCosts(COUNT_PAGE, SIZE_PAGE, SORT_DESC, FILTER));
    }

    @Test
    void shouldReturnAllOrderCarUserDto_WhenSortedByCostsAsc(){
        when(dao.getOrderCarUserListSortedByCostsAsc(COUNT_PAGE, SIZE_PAGE,FILTER)).thenReturn(orderCarUserDtoList);
        assertSame(orderCarUserDtoList , taxiOrderService.getAllTaxiOrderSortedByCosts(COUNT_PAGE, SIZE_PAGE, SORT_ASC, FILTER));
    }

    @Test
    void shouldReturnAllOrderCarUserDto_WhenSortedByTimeDesc(){
        when(dao.getOrderCarUserListSortedByDataDesc(COUNT_PAGE, SIZE_PAGE,FILTER)).thenReturn(orderCarUserDtoList);
        assertSame(orderCarUserDtoList , taxiOrderService.getAllTaxiOrderSortedByTime(COUNT_PAGE, SIZE_PAGE, SORT_DESC, FILTER));
    }

    @Test
    void shouldReturnAllOrderCarUserDto_WhenSortedByTimeAsc(){
        when(dao.getOrderCarUserListSortedByDataAsc(COUNT_PAGE, SIZE_PAGE,FILTER)).thenReturn(orderCarUserDtoList);
        assertSame(orderCarUserDtoList , taxiOrderService.getAllTaxiOrderSortedByTime(COUNT_PAGE, SIZE_PAGE, SORT_ASC, FILTER));
    }

    @Test
    void shouldReturnStatisticsDtoList(){
        when(dao.getTotalCostsAndDistance(COUNT_PAGE, SIZE_PAGE)).thenReturn(statisticDtoList);
        assertSame(statisticDtoList , taxiOrderService.getStatistics(COUNT_PAGE, SIZE_PAGE));
    }

    @Test
    void shouldReturnCarsCountOrders(){
        when(dao.getCarCountInOrders()).thenReturn(COUNT);
        assertEquals(COUNT , taxiOrderService.getCarsCountInOrder());
    }

    @Test
    void shouldReturnNumberOfOrdersByUser(){
        when(dao.getOrdersCountByUser(USER_ID)).thenReturn(COUNT);
        assertEquals(COUNT , taxiOrderService.getNumberOfOrdersByUser(USER_ID));
    }

    @Test
    void shouldReturnNumbersOfOrders(){
        when(dao.count()).thenReturn(COUNT);
        assertEquals(COUNT , taxiOrderService.getNumberOfOrders());
    }

    @Test
        void shouldCreateTaxiOrder(){
        TaxiOrderDto toCreate = new TaxiOrderDto();
        toCreate.setArrival(ARRIVAL);
        toCreate.setDeparture(DEPARTURE);
        TaxiOrder orderToCreate = new TaxiOrder();
        orderToCreate.setArrival(ARRIVAL);
        orderToCreate.setDeparture(DEPARTURE);
        when(dao.create(orderToCreate)).thenReturn(true);
        assertTrue(taxiOrderService.createTaxiOrder(toCreate, USER_ID , CAR_ID));
    }
}