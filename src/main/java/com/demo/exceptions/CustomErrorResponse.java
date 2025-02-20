package com.demo.exceptions;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.validation.FieldError;

public class CustomErrorResponse {
	
	    private LocalDateTime timestamp;
	    private int status;
	    private String error;
	    private List<FieldError> errorDetails; 

	    public CustomErrorResponse(LocalDateTime timestamp, int status, String error, List<FieldError> errorDetails) {
	        this.timestamp = timestamp;
	        this.status = status;
	        this.error = error;
	        this.errorDetails = errorDetails;
	    }

	    public LocalDateTime getTimestamp() {
	        return timestamp;
	    }

	    public int getStatus() {
	        return status;
	    }

	    public String getError() {
	        return error;
	    }

	    public List<FieldError> getErrorDetails() {
	        return errorDetails;
	    }


}





