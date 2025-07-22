package br.com.rafaoliveira.controllers;

import br.com.rafaoliveira.data.dto.v1.PersonDTOV1;
import br.com.rafaoliveira.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.rafaoliveira.util.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v1")
@Tag(name = "People", description = "Endpoint for Managing People")
public class PersonController {
    @Autowired
    private PersonServices service;

    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(
            summary = "Finds all People",
            description = "Finds all people",
            tags = {"People"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonDTOV1.class)))
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public List<PersonDTOV1> findByAll()  {
        return service.findByAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(
            summary = "Finds a Person",
            description = "Finds a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTOV1.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTOV1 findById(@PathVariable Long id)  {
        return service.findById(id);
    }

    @PostMapping(
            value = "/",
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
    public PersonDTOV1 create(@RequestBody PersonDTOV1 person)  {
        return service.create(person);
    }

    @PutMapping(
            value = "/",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(
            summary = "Updates a Person",
            description = "Updates a  Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTOV1.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTOV1 update(@RequestBody PersonDTOV1 person)  {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Deletes a Person",
            description = "Deletes a  Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> delete(@PathVariable Long id)  {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
