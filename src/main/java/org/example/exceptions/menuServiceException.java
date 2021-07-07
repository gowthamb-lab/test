package org.example.exceptions;

public class menuServiceException extends RuntimeException{
    public menuServiceException(String message,Throwable root){
        super(message,root);
    }

}
