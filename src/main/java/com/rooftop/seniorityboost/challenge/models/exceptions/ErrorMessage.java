package com.rooftop.seniorityboost.challenge.models.exceptions;

public class ErrorMessage {

    private boolean error;
    private String message;
    private Integer code;
    

    public ErrorMessage(boolean error, String message, Integer code) {
        super();
        this.error = error;
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

   

}
