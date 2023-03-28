package com.mjc.school.repository;

import java.util.List;

public interface Repository<T> {

    List<T> readAllNews();

    T readByIdNews(Long id);

    T createNews(T data);

    T updateNews(T data);

    Boolean deleteNewsById(Long id);

    Boolean checkNewsById(Long id);

}
