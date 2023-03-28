package com.mjc.school.service.interfaces;

import java.util.List;

public interface NewsService<T1, T2> {

    List<T2> readAllNews();

    T2 readNewsById(Long id);

    T2 createNews(T1 data);

    T2 updateNews(T1 data);

    Boolean deleteNewsById(Long id);
}
