package com.service123.tiketing.controller.exception;

public class DuplicateUserNameException extends Exception{
    @Override
    public String getMessage() {
        return "Duplicate User Name";
    }
}
