package com.udj.course.resources.Exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Integer errorType, String message, String moment) {
        super(errorType, message, moment);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String fieldMessage){
        errors.add(new FieldMessage(fieldName, fieldMessage));
    }
}
