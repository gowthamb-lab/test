package org.example.exceptions;

public class reservationServiceException extends RuntimeException{
    public reservationServiceException(String message,Throwable root){
        super(message,root);
    }
}
