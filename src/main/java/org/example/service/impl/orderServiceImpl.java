package org.example.service.impl;

import org.example.Repository.orderRepository;
import org.example.dtos.ordersDto;
import org.example.exceptions.*;
import org.example.model.entity.Orders;
import org.example.service.orderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class orderServiceImpl implements orderService {

    @Autowired
    private orderRepository ordersRepo;

    private ordersDto convertEntitytoDTO(Orders orders) {
        ordersDto ordersDto = new ordersDto();
        BeanUtils.copyProperties(orders, ordersDto);
        return ordersDto;
    }

    private Orders convertDTOtoEntity(ordersDto orderDto){
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderDto,orders);
        return orders;
    }

    @Override
    public List<Orders> getAllOrders(int pageNo, int size) {

        Pageable paging = PageRequest.of(pageNo, size);

        try{
            System.out.println(ordersRepo.findAll());
            List<Orders> adm= (List<Orders>) ordersRepo.findAll(paging);
            return adm;
        }
        catch(Exception e)
        {
            System.out.println("location not found");
            throw new orderServiceException("There are no orders stored",e);
        }
    }

    @Override
    public ordersDto createOrder(ordersDto orderDto) {

        try{
            Orders ord =  convertDTOtoEntity(orderDto);
            Orders responseEntity = ordersRepo.save(ord);
            orderDto= convertEntitytoDTO(responseEntity);
        }
        catch(Exception e)
        {
            System.out.println("order not created");
            throw new orderServiceException("Requested order cannot be created",e);
        }
        return orderDto;
    }

    @Override
    public List<Orders> getAllOrderDetails(Date date, String locationId) {
        try{
            List<Orders> adm= (List<Orders>) ordersRepo.findAllByDateAndLocalityLocationId(date,locationId);
            return adm;
        }
        catch(Exception e)
        {
            throw new orderServiceException("Admin not found",e);
        }
    }

    @Override
    public Orders getOrdersById(String orderId) {
        Optional<Orders> ord = ordersRepo.findById(orderId);
        if(!ord.isPresent())
        {
            throw new OrderNotFoundException("Requested admin account cannot be created");
        }
        return ord.get();

    }
}
