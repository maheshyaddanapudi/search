package com.my.searcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.searcher.constants.Constants;
import com.my.searcher.dto.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController(Constants.CACHE_CONTROLLER)
@Tag(name = "Cache Clear Actions", description = "Functionality for triggering Cache Clear after fresh data load.")
public class CacheController {
	
	@Autowired
	CacheManager cacheManager;

	@GetMapping(Constants.CACHE_CLEAR)
	 @Operation(summary = "Triggers a Cache Clearance", description = "Returns Status of Clearance Trigger", tags = { "cache" })
	  @ApiResponses(value = {
	          @ApiResponse(responseCode = "200", description = "Successfully Triggered Cache Clearance",
	                  content = @Content(schema = @Schema(implementation = BaseResponse.class))) ,
	          @ApiResponse(responseCode = "500", description = "Internal Server Error - Returned when an unexpected error occurs on server side", content = @Content())})
	  @ResponseBody
	public ResponseEntity<BaseResponse> clear() {
	    cacheManager.getCache(Constants.CACHE_NAME).clear();
	    return new ResponseEntity<BaseResponse>(new BaseResponse("Triggered Cache Clear Successfully !!!", true), HttpStatus.OK);
	}
}
