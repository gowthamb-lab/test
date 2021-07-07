package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class AdminServiceException extends RuntimeException{

    public AdminServiceException(String message,Throwable root){
        super(message,root);
    }



}
