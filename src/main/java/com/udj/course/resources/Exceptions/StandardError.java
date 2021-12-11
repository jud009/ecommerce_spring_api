package com.udj.course.resources.Exceptions;

import java.io.Serializable;


public class StandardError implements Serializable {

    private Integer errorType;
    private String message;
    private String moment;

    public StandardError(Integer errorType, String message, String moment) {
        this.errorType = errorType;
        this.message = message;
        this.moment = moment;
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }
}
