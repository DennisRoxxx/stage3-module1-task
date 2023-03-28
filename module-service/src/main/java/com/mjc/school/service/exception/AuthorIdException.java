package com.mjc.school.service.exception;

public class AuthorIdException extends CustomException {
    public AuthorIdException(String message) {
        super(message);
    }
    public static String getCode() {
        return "ERROR_CODE: 000002  ERROR_MESSAGE: Author Id does not exist. Author Id is %s";
    }
}
