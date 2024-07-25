package org.example.aston_trainee_hw3.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an error response object that will be returned to the client in JSON format.
 * Contains fields for HTTP status code and error message.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;

}
