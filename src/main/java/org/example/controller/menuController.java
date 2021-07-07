package org.example.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.dtos.MenuDto;

import org.example.model.entity.Menu;
import org.example.model.entity.locations;
import org.example.responses.Response;
import org.example.responses.ResponseMetadata;
import org.example.responses.StatusMessage;
import org.example.service.menuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class menuController {

    @Autowired
    private menuService service;


    @GetMapping(value="/menu/{day}")
    @Operation(summary = "returns the menu for the day in that location", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<Menu> getMenuByDayAndLocation(String day, locations Locality  )
    {
        return Response.<Menu>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((service.getMenuByDayAndLocation(day,Locality)))
                .build();
    }



    @PostMapping(value="/menu")
    @Operation(summary = "Creates the menu for specific location and day", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<MenuDto> createMenu(@RequestBody MenuDto menuDto)
    {
        return Response.<MenuDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((service.createMenu(menuDto)))
                .build();
    }

    @PutMapping(value="/menu")
    @Operation(summary = "Creates the menu for specific location and day", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<Menu> updateMenu(@PathVariable("id") String id, @RequestBody Menu menu)
    {
        return Response.<Menu>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((service.UpdateMenu(id,menu)))
                .build();
    }



}
