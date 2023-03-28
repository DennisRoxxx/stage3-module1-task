package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exception.*;

import java.util.Scanner;

public class Verification {

    public static long checkUserId(Scanner input) {
        try {
            long userId = input.nextLong();
            input.nextLine();
            return userId;
        } catch (Exception e) {
            input.nextLine();
            try {
                throw new AuthorNameException(String.format(AuthorNameException.getCode(),input));
            } catch (AuthorNameException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void verificationNewsId(Long id) {
        if (id == null || id < 1) {
            throw new NewsNotFoundException(String.format(NewsNotFoundException.getCode(),id));
        }
    }
    public void verificationAuthorId(Long id) {
        if (id > 20) {
            throw new AuthorIdException(String.format(AuthorIdException.getCode(),id));
        }
    }
    public void lengthTitle(String title) {
        if ((title == null) || (title.trim().length() < 5 || title.trim().length() > 30)) {
            throw new LengthTitleException(String.format(LengthTitleException.getCode(),title));
        }
    }
    public void lengthContent(String content) {
        if ((content == null) || (content.trim().length() < 5 || content.trim().length() > 255)) {
            throw new LengthContentException(String.format(LengthContentException.getCode(),content));
        }
    }

    public void verificationDtoRequest(NewsDtoRequest newsDtoRequest) {
        verificationNewsId(newsDtoRequest.getAuthorId());
        verificationAuthorId(newsDtoRequest.getAuthorId());
        lengthTitle(newsDtoRequest.getTitle());
        lengthContent(newsDtoRequest.getContent());
    }


}
