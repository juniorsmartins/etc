package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntity;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTORequest;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTOResponse;
import io.portfolio.micro_cliente.client.domain.filter.ClientPersonFilter;
import io.portfolio.micro_cliente.client.domain.services.PolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "${app.api.base}/clients/persons", produces = {"application/json"})
@Tag(name = "Controller ClientPerson")
public final class ClientPersonController extends PolicyControllers<ClientPersonDTORequest, ClientPersonFilter, ClientPersonDTOResponse, Long> {

    private static Logger log = LoggerFactory.getLogger(ClientPersonController.class);

    @Autowired
    private PolicyService<ClientPersonDTORequest, ClientPersonFilter, ClientPersonDTOResponse, ClientPersonEntity, Long> service;

    @Operation(summary = "Create", description = "register resource in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created - successful request and new resource created."),
            @ApiResponse(responseCode = "400", description = "Bad Request - request with invalid syntax."),
            @ApiResponse(responseCode = "409", description = "Conflict - business rule violated."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - server in unforeseen situation.")
    })
    @Override
    public ResponseEntity<ClientPersonDTOResponse> create(
            @Parameter(name = "ClientPersonDTORequest", description = "structure for transporting data.", required = true)
            @RequestBody @Valid ClientPersonDTORequest dto) {
        log.info("Started resource record control.");

        var response = this.service.create(dto);

        log.info("Return - completed resource registration.");
        return ResponseEntity
                .created(URI.create("/"+ response.id()))
                .body(response);
    }

    @Operation(summary = "Update", description = "update resource in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - successful request."),
            @ApiResponse(responseCode = "400", description = "Bad Request - request with invalid syntax."),
            @ApiResponse(responseCode = "404", description = "Not Found - resource not found in database."),
            @ApiResponse(responseCode = "409", description = "Conflict - business rule violated.")
    })
    @Override
    public ResponseEntity<ClientPersonDTOResponse> update(
            @Parameter(name = "ClientPersonDTORequest", description = "structure for transporting data.", required = true)
            @RequestBody @Valid ClientPersonDTORequest dto) {
        log.info("Started resource update control.");

        var response = this.service.update(dto);

        log.info("Return - completed resource update");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Operation(summary = "SearchById", description = "search a resource in the database by identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - successful request."),
            @ApiResponse(responseCode = "400", description = "Bad Request - request with invalid syntax."),
            @ApiResponse(responseCode = "404", description = "Not Found - resource not found in database.")
    })
    @Override
    public ResponseEntity<ClientPersonDTOResponse> searchById(
            @Parameter(name = "id", description = "identifying key", example = "22", required = true)
            @PathVariable(value = "id") Long id) {
        log.info("Started resource fetch control by identifier.");

        var response = this.service.searchById(id);

        log.info("Return - completed resource lookup by identifier.");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Operation(summary = "SearchAll", description = "search all resources from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - successful request."),
            @ApiResponse(responseCode = "400", description = "Bad Request - request with invalid syntax.")
    })
    @Override
    public ResponseEntity<Page<ClientPersonDTOResponse>> searchAll(
            @Parameter(name = "ClientPersonFilter", description = "dynamic search by parameters", required = false) ClientPersonFilter filter,
            @Parameter(name = "Pageable", description = "search with custom pagination", required = false)
       @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        log.info("Started search control all resources.");

        var response = this.service.searchAll(filter, pagination);

        log.info("Return - completed search control all resources.");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Operation(summary = "DeleteById", description = "delete resource from database by identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - successful request."),
            @ApiResponse(responseCode = "400", description = "Bad Request - request with invalid syntax."),
            @ApiResponse(responseCode = "404", description = "Not Found - resource not found in database.")
    })
    @Override
    public ResponseEntity<String> deleteById(
            @Parameter(name = "id", description = "identifying key", example = "22", required = true)
            @PathVariable(value = "id") Long id) {
        log.info("Started resource deletion control by identifier.");

        var response = this.service.deleteById(id);

        log.info("Return - completed delete resource by identifier.");
        return ResponseEntity
                .ok()
                .body(response);
    }
}
