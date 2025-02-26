package br.com.rafaoliveira.controllers;

import br.com.rafaoliveira.data.dto.v1.PersonDTOV1;
import br.com.rafaoliveira.data.dto.v2.PersonDTOV2;
import br.com.rafaoliveira.services.PersonServices;
import br.com.rafaoliveira.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person/v2")
public class PersonControllerV2 {
    @Autowired
    private PersonServices service;

    @PostMapping(
            value = "/v2",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(
            summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTOV1.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person)  {
        return service.createV2(person);
    }
}
