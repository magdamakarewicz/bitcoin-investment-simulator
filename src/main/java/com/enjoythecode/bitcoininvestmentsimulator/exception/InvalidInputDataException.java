package com.enjoythecode.bitcoininvestmentsimulator.exception;

/**
 * Exception class that represents an invalid input data error.
 */
public class InvalidInputDataException extends Exception {

    /**
     * Constructs a new InvalidInputDataException with the specified error message.
     *
     * @param message the error message that describes the reason for the exception
     */
    public InvalidInputDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidInputDataException with the specified error message and cause.
     *
     * @param message the error message that describes the reason for the exception
     * @param cause   the cause of the exception
     */
    public InvalidInputDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
