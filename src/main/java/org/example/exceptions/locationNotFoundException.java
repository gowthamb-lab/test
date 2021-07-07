package org.example.exceptions;

public class locationNotFoundException extends RuntimeException{

    public locationNotFoundException(String message){
        super(message);
    }
}
