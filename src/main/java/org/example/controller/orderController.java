package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.dtos.MenuDto;
import org.example.dtos.ordersDto;
import org.example.model.entity.Orders;
import org.example.responses.Response;
import org.example.responses.ResponseMetadata;
import org.example.responses.StatusMessage;
import org.example.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class orderController {

    @Autowired
    private orderService service;

    @GetMapping(value="/orders")
    public Response<List<Orders>> getAllOrders(@RequestParam(defaultValue = "0")int pageNo,
                                               @RequestParam(defaultValue = "10")int pageSize  )
    {
        return Response.<List<Orders>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((service.getAllOrders(pageNo,pageSize)))
                .build();
    }


//    ordersDto createOrder(ordersDto orderDto);

    @PostMapping(value="/orders")
    @Operation(summary = "Creates the order for the specific customer", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<ordersDto> createMenu(@RequestBody ordersDto orderDto)
    {
        return Response.<ordersDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((service.createOrder(orderDto)))
                .build();
    }


    @GetMapping(value="/orders/{id}")
    @Operation(summary = "returns the order details for the given orderId", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })


    public Response<Orders> getOrderById(@PathVariable("id") String id)
    {
        return Response.<Orders>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((service.getOrdersById(id)))
                .build();
    }

    @GetMapping(value="/orders/{date}/{id}")
    @Operation(summary = "returns the order details for the given date and locationId", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })



    public Response<List<Orders>> getOrderByIdAndLocalityId(Date date,@PathVariable("id") String locationId)
    {
        return Response.<List<Orders>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((service.getAllOrderDetails(date,locationId)))
                .build();
    }
}
