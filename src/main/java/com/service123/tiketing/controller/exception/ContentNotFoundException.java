package com.service123.tiketing.controller.exception;

public class ContentNotFoundException extends Exception{
    private String message;
    public ContentNotFoundException() {
        this.message=" Content Not Found";
    }

    public ContentNotFoundException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
