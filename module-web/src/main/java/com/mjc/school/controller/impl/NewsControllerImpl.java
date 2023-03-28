package com.mjc.school.controller.impl;


import com.mjc.school.controller.NewsController;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.interfaces.NewsService;
import com.mjc.school.service.impl.NewsServiceImpl;
import java.util.List;



public class NewsControllerImpl implements NewsController<NewsDtoRequest, NewsDtoResponse> {

    private final NewsService<NewsDtoRequest, NewsDtoResponse> newsService = NewsServiceImpl.getNewsServiceImpl();

    @Override
    public List<NewsDtoResponse> readAllNews() {
        return newsService.readAllNews();
    }

    @Override
    public NewsDtoResponse readNewsById(Long id) {
        return newsService.readNewsById(id);
    }

    @Override
    public NewsDtoResponse createNews(NewsDtoRequest request) {
        return newsService.createNews(request);
    }

    @Override
    public NewsDtoResponse updateNews(NewsDtoRequest request) {
        return newsService.updateNews(request);
    }

    @Override
    public Boolean deleteNewsById(Long id) {
        return newsService.deleteNewsById(id);
    }


}
