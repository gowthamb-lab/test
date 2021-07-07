package org.example.service;

import org.example.dtos.ordersDto;
import org.example.model.entity.Orders;

import java.util.Date;
import java.util.List;

public interface orderService {

    List<Orders> getAllOrders(int pageNo, int pageSize);
    ordersDto createOrder(ordersDto orderDto);
    List<Orders> getAllOrderDetails(Date date, String locationId);
    Orders getOrdersById(String orderId);
}
