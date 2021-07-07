package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.dtos.AdministratorDto;
import org.example.model.entity.Administrator;
import org.example.responses.Response;
import org.example.responses.ResponseMetadata;
import org.example.responses.StatusMessage;
import org.example.service.administratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class administratorController {


    @Autowired
    private administratorService adminService;

    @GetMapping(value="/admin")
    @Operation(summary = "return all the admins", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<List<Administrator>> getAllAdmins(@RequestParam(defaultValue = "0")Integer pageNo,
                                                      @RequestParam(defaultValue = "10")Integer pageSize  )
    {
        return Response.<List<Administrator>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((adminService.getAllAdmins(pageNo,pageSize)))
                .build();
    }

    @PostMapping(value="/admin")
    @Operation(summary = "Create admins", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<String> createAdmins(@RequestBody AdministratorDto adminDto)
    {
        return adminService.createAdmins(adminDto) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Order Created")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Order Not Created")
                        .build();
    }

    @PutMapping(value="/admin")
    @Operation(summary = "Update the admin Details", responses = {
            @ApiResponse(description = "Successful Operation", responseCode ="200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })

    public Response<Administrator> updateAdmins( @RequestBody Administrator admin,@PathVariable("id") String id)
    {
        return Response.<Administrator>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((adminService.updateAdmins(id,admin)))
                .build();
    }
}
