package com.my.searcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.searcher.constants.Constants;
import com.my.searcher.dto.request.RecordDetail;
import com.my.searcher.dto.response.BaseResponse;
import com.my.searcher.service.data.db.RecordDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController(Constants.RECORD_CONTROLLER)
@Tag(name = "Records Actions", description = "Functionality for adding / updating / deleting records from Database.")
public class RecordController {
	
	@Autowired
	RecordDataService recordDataService;

	@PostMapping(value = Constants.RECORD_ADD, consumes = "application/json", produces = "application/json")
	 @Operation(summary = "Adds new record Info", description = "Returns JSON formatted Record Id and status of action", tags = { "record" })
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Successfully captured record data",
                   content = @Content(schema = @Schema(implementation = BaseResponse.class))) ,
           @ApiResponse(responseCode = "400", description = "Bad Request - Invalid Date Format (Most Common Reason)", content = @Content(schema = @Schema(implementation = BaseResponse.class)))})
   @ResponseBody
	public ResponseEntity<BaseResponse> add(@RequestBody RecordDetail request) {
		
		BaseResponse response = recordDataService.add(request);
		
		if(response.isStatus())
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		else
			return new ResponseEntity<BaseResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = Constants.RECORD_UPDATE + "/{" + Constants.RECORD_ID + "}", consumes = "application/json", produces = "application/json")
	 @Operation(summary = "Updates record Info", description = "Returns JSON formatted Record Id and status of action", tags = { "record" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully captured record data",
                  content = @Content(schema = @Schema(implementation = BaseResponse.class))) ,
          @ApiResponse(responseCode = "400", description = "Bad Request - Invalid Date Format (Most Common Reason)", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
          @ApiResponse(responseCode = "404", description = "Not Found - Record Not Found (Most Common Reason)", content = @Content(schema = @Schema(implementation = BaseResponse.class)))})
  @ResponseBody
	public ResponseEntity<BaseResponse> update(@PathVariable int recordId, @RequestBody RecordDetail request) {
		
		BaseResponse response = recordDataService.update(recordId, request);
		
		if(response.isStatus())
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		else if(response.getMessage().equalsIgnoreCase("404"))
		{
			response.setMessage("Invalid Record ID !!!");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity<BaseResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = Constants.RECORD_DELETE + "/{" + Constants.RECORD_ID + "}", produces = "application/json")
	 @Operation(summary = "Deletes record Info", description = "Returns JSON formatted Record Id and status of action", tags = { "record" })
	 @ApiResponses(value = {
	         @ApiResponse(responseCode = "200", description = "Successfully deleted record data",
	                 content = @Content(schema = @Schema(implementation = BaseResponse.class))) ,
	         @ApiResponse(responseCode = "404", description = "Not Found - Record Not Found (Most Common Reason)", content = @Content(schema = @Schema(implementation = BaseResponse.class)))})
	 @ResponseBody
	public ResponseEntity<BaseResponse> delete(@PathVariable int recordId) {
		
		BaseResponse response = recordDataService.delete(recordId);
		
		if(response.isStatus())
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		else
		{
			return new ResponseEntity<BaseResponse>(response, HttpStatus.NOT_FOUND);
		}
	}
}
