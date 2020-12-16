package com.example.postgres.springpostgresdocker.exception;

import java.time.OffsetDateTime;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
public class ErrorDetails {
    private OffsetDateTime dateTime;
    private String message;
    private String errorDetails;

    public ErrorDetails(OffsetDateTime dateTime, String message, String errorDetails) {
        super();
        this.dateTime = dateTime;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
