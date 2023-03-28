package com.mjc.school.service.exception;

public class NewsNotFoundException extends CustomException {
    public NewsNotFoundException(String message) {
        super(message);
    }
    public static String getCode() {
        return "ERROR_CODE: 000001  ERROR_MESSAGE: Content with id % does not exist.";
    }}