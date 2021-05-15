package com.my.searcher.rest;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.searcher.constants.Constants;
import com.my.searcher.service.search.es.SearcherService;
import com.my.searcher.utils.ResultQuery;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController(Constants.SEARCH_CONTROLLER)
@Tag(name = "Search Actions", description = "Functionality for searching records from Elasticsearch data.")
public class SeachController {
	
	@Autowired
	SearcherService searcherService;

	@GetMapping(value = Constants.SEARCH_QUERY + "/{"+Constants.QUERY+"}", produces = "application/json")
	 @Operation(summary = "Provides Records Info", description = "Returns JSON formatted Records Info", tags = { "search" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned Product Info",
                    content = @Content(schema = @Schema(implementation = ResultQuery.class))) ,
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Returned when an unexpected error occurs on server side", content = @Content())})
    @ResponseBody
    public ResponseEntity<ResultQuery> searchQuery(
    		@PathVariable String query, 
    		@RequestParam(name = "from", required = false, defaultValue = "0") int from, 
    		@RequestParam(name = "size", required = false, defaultValue = "100") int size,
    		@RequestParam(name = "field", required = false) String field,
    		@RequestParam(name = "fields", required = false) String[] fields
    		) 
    				throws IOException {
		
		if(null!=fields && fields.length > 0 && from > 0 && size > 0)
			return new ResponseEntity<> (searcherService.searchFromQuery(query.trim().toLowerCase(),  Arrays.asList(fields), from, size), HttpStatus.OK);
		
		else if(null!=fields && fields.length > 0)
			return new ResponseEntity<> (searcherService.searchFromQuery(query.trim().toLowerCase(), Arrays.asList(fields)), HttpStatus.OK);
		
		else if(null!=field && from > 0 && size > 0)
			return new ResponseEntity<> (searcherService.searchFromQuery(query.trim().toLowerCase(), field, from, size), HttpStatus.OK);
		
		else if(null!=field)
			return new ResponseEntity<> (searcherService.searchFromQuery(query.trim().toLowerCase(), field), HttpStatus.OK);
		
		else if(from > 0 && size > 0)
			return new ResponseEntity<> (searcherService.searchFromQuery(query.trim().toLowerCase(), from, size), HttpStatus.OK);
		
		else
			return new ResponseEntity<> (searcherService.searchFromQuery(query.trim().toLowerCase()), HttpStatus.OK);
    }
}
