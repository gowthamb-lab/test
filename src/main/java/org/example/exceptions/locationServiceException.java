package org.example.exceptions;

public class locationServiceException extends RuntimeException{
    public locationServiceException(String message,Throwable root){
        super(message,root);
    }

}
