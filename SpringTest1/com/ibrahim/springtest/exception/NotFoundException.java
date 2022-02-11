package com.ibrahim.springtest.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("No entity found");
    }
}
