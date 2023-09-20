package com.service123.tiketing.controller.exception;

public class DisabledUserException extends Exception {
    @Override
    public String getMessage() {
        return "User is Disabled";
    }
}
