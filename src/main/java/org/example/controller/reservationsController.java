package org.example.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.dtos.ordersDto;
import org.example.dtos.reservationsDto;
import org.example.model.entity.Menu;
import org.example.model.entity.Orders;
import org.example.model.entity.reservations;
import org.example.responses.Response;
import org.example.responses.ResponseMetadata;
import org.example.responses.StatusMessage;
import org.example.service.reservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class reservationsController {
    @Autowired
    private reservationService bookingService;
    @GetMapping(value="/reservations")
    public Response<List<reservations>> getAllreservations(@RequestParam(defaultValue = "0")int pageNo,
                                               @RequestParam(defaultValue = "10")int pageSize  )
    {
        return Response.<List<reservations>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((bookingService.getAllReservations(pageNo,pageSize)))
                .build();
    }
@PostMapping(value="/reservations")
@Operation(summary = "Creates the reservation for the specific customer", responses = {
        @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
public Response<reservationsDto> createMenu(@RequestBody reservationsDto bookingDto)
{
    return Response.<reservationsDto>builder()
            .meta(ResponseMetadata.builder()
                    .statusCode(200)
                    .statusMessage(StatusMessage.SUCCESS.name()).build())
            .data((bookingService.createReservation(bookingDto)))
            .build();
}

    @PutMapping(value="/reservations/{id}")
    @Operation(summary = "Update the reservation made", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<reservations> updateMenu(@PathVariable("id") String id, @RequestBody  reservations bookings)
    {
        return Response.<reservations>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((bookingService.updateReservation(id,bookings)))
                .build();
    }

@DeleteMapping(value="/reservations/{id}")
@Operation(summary = "Update the reservation made", responses = {
        @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
public Response<String > deleteReservation(@PathVariable("id") String id)
{
    return bookingService.cancelReservation(id) == Boolean.TRUE ?
            Response.<String>builder()
                    .meta(ResponseMetadata.builder()
                            .statusCode(200)
                            .statusMessage(StatusMessage.SUCCESS.name()).build())
                    .data("reservation cancelled")
                    .build()
            :
            Response.<String>builder()
                    .meta(ResponseMetadata.builder()
                            .statusCode(200)
                            .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                    .data("Couldn't cancel the reservation")
                    .build();
}
}
