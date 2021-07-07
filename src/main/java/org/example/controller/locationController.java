package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.dtos.locationsDto;
import org.example.model.entity.locations;
import org.example.responses.Response;
import org.example.responses.ResponseMetadata;
import org.example.responses.StatusMessage;
import org.example.service.locationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class locationController {

    @Autowired
    private locationsService localService;

    @PostMapping(value="/locality")
    @Operation(summary = "create a new location", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })

    public Response<String> createLocations(@RequestBody locationsDto localityDto)
    {
        return localService.createLocation(localityDto) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("location Created")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Location Not Created")
                        .build();
    }

    @GetMapping(value="/locality")
    @Operation(summary = "get all the locations", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })

    public Response<List<locations>> getAllLocations(@RequestParam(defaultValue = "0")int pageNo,
                                                     @RequestParam(defaultValue = "10")int pageSize ){

        return Response.<List<locations>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((localService.getAllLocations(pageNo,pageSize)))
                .build();
    }

    @GetMapping(value="/locality/{id}")
    @Operation(summary = "get the location by id", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })

    public Response<locations> getLocationsById(@PathVariable("id") String id)
    {
        return Response.<locations>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((localService.getLocationById(id)))
                .build();
    }

    @PutMapping(value="/locality/{id}")
    @Operation(summary = "update the location by Id", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })

    public Response<locations> updateLocationsById(@PathVariable("id") String id,@RequestBody locations loc)
    {
        return Response.<locations>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((localService.updateLocation(id,loc)))
                .build();
    }
    @DeleteMapping(value="/locality/{id}")
    @Operation(summary = "delete the location by id", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })

    public Response<String> deleteLocationsById(@PathVariable("id") String id)
    {
        return localService.deleteLocation(id) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("location Deleted")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Location Not Deleted")
                        .build();
    }

}
