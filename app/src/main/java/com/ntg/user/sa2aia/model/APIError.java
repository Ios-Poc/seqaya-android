package com.ntg.user.sa2aia.model;

/**
 * Created by ilias on 05/02/2018.
 */

public class APIError {

    private int code;
    private String message;

    public APIError() {
    }

    public APIError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
