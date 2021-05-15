package com.my.searcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.searcher.constants.Constants;
import com.my.searcher.dto.response.BaseResponse;
import com.my.searcher.service.data.es.ElasticSynchronizer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController(Constants.SYNC_CONTROLLER)
@Tag(name = "Elasticsearch Sync Actions", description = "Functionality for triggering Elasticsearch - Database Sync")
public class ElasticsearchSyncController {
	
	@Autowired
	ElasticSynchronizer elasticSynchronizer;

	@GetMapping(Constants.SYNC_TRIGGER)
	 @Operation(summary = "Triggers a sync between database and Elasticsearch", description = "Returns Status of Sync Trigger", tags = { "sync" })
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Successfully Triggered Elasticsearch Sync",
                   content = @Content(schema = @Schema(implementation = BaseResponse.class))) ,
           @ApiResponse(responseCode = "500", description = "Internal Server Error - Returned when an unexpected error occurs on server side", content = @Content())})
   @ResponseBody
   public ResponseEntity<BaseResponse> sync(){
		elasticSynchronizer.sync();
		return new ResponseEntity<BaseResponse>(new BaseResponse("Triggered Sync Successfully !!!", true), HttpStatus.OK);
	}
}
