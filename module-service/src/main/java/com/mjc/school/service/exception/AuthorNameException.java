package com.mjc.school.service.exception;

import java.io.IOException;

public class AuthorNameException extends IOException {
    public AuthorNameException(String message) {
        super(message);
    }
    public static String getCode() {
        return "ERROR_CODE: 000013  ERROR_MESSAGE: Author Id should be number";
    }
}
