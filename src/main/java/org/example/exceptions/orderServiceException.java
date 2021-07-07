package org.example.exceptions;

public class orderServiceException extends RuntimeException{
    public orderServiceException(String message,Throwable root){
        super(message,root);
    }


}
