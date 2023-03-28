package com.mjc.school.service.exception;

public class LengthTitleException extends CustomException {
    public LengthTitleException(String message) {
        super(message);
    }

    public static String getCode() {
        return "ERROR_CODE: 000012  ERROR_MESSAGE: Content title can not be less than 5 and more than 30 symbols. Content title is %s";
    }
}
