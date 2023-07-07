package com.example.user_management.infrastructure.constant;

import com.example.user_management.util.PropertiesReader;

public enum Message {

    SUCCESS("Success"),

    ERROR_UNKNOWN("Error Unknown"),
    USER_NOT_EXISTS(PropertiesReader.getProperty(PropertyKeys.USER_NOT_EXISTS)),
    USER_NOT_ALLOWED(PropertiesReader.getProperty(PropertyKeys.USER_NOT_ALLOWED)),
    INVALID_TOKEN(PropertiesReader.getProperty(PropertyKeys.INVALID_TOKEN));

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
