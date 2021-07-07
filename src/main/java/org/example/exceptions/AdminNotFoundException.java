package org.example.exceptions;

public class AdminNotFoundException extends RuntimeException{

    public AdminNotFoundException(String message){
        super(message);
    }
}
