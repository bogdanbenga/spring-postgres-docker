package com.example.postgres.springpostgresdocker.exception;

import java.util.Date;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
public class ErrorDetails {
    private Date dateTime;
    private String message;
    private String errorDetails;

    public ErrorDetails(Date dateTime, String message, String errorDetails) {
        super();
        this.dateTime = dateTime;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
