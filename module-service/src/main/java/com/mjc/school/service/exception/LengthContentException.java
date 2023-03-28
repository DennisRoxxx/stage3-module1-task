package com.mjc.school.service.exception;

public class LengthContentException extends CustomException {
    public LengthContentException(String message) {
        super(message);
    }

    public static String getCode() {
        return "ERROR_CODE: 000012  ERROR_MESSAGE: Content content can not be less than 5 and more than 255 symbols. Content content is %s";
    }
}
