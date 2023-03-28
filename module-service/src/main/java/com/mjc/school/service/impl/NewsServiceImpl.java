package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.RepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.Repository;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.NewsNotFoundException;
import com.mjc.school.service.interfaces.NewsService;
import com.mjc.school.service.Verification;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.modelmapper.ModelMapper;

public class NewsServiceImpl implements NewsService<NewsDtoRequest, NewsDtoResponse> {

        private static NewsServiceImpl newsServiceImpl;
        private final Verification verification;
        private final ModelMapper mapper;
        private final Repository<NewsModel> newsRepository;


        public static NewsServiceImpl getNewsServiceImpl() {
            if (newsServiceImpl == null) {
                newsServiceImpl = new NewsServiceImpl();
            }
            return newsServiceImpl;
        }

        public NewsServiceImpl() {
            mapper = new ModelMapper();
            newsRepository = new RepositoryImpl();
            verification = new Verification();
        }

        @Override
        public List<NewsDtoResponse> readAllNews() {
            return newsRepository.readAllNews().stream()
                    .map(news -> mapper.map(news, NewsDtoResponse.class))
                    .toList();
        }

        @Override
        public NewsDtoResponse readNewsById(Long id) {
            verification.verificationNewsId(id);
            if (newsRepository.checkNewsById(id)) {
                NewsModel news = newsRepository.readNewsById(id);
                return mapper.map(news, NewsDtoResponse.class);
            } else {
                throw new NewsNotFoundException(String.format(NewsNotFoundException.getCode(),id));
            }
        }

        @Override
        public NewsDtoResponse createNews(NewsDtoRequest newsDtoRequest) {
            verification.verificationDtoRequest(newsDtoRequest);
            NewsModel toCreateNews = mapper.map(newsDtoRequest, NewsModel.class);
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            toCreateNews.setCreateDate(now);
            toCreateNews.setLastUpdateDate(now);
            NewsModel createdNews = newsRepository.createNews(toCreateNews);
            return mapper.map(createdNews, NewsDtoResponse.class);
        }

        @Override
        public NewsDtoResponse updateNews(NewsDtoRequest newsDtoRequest) {
            verification.verificationDtoRequest(newsDtoRequest);
            if (newsRepository.checkNewsById(newsDtoRequest.getId())) {
                NewsModel toCreateNews = mapper.map(newsDtoRequest, NewsModel.class);
                LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                toCreateNews.setLastUpdateDate(now);
                NewsModel createdNews = newsRepository.updateNews(toCreateNews);
                return mapper.map(createdNews, NewsDtoResponse.class);
            } else {
                throw new NewsNotFoundException(String.format(NewsNotFoundException.getCode(),newsDtoRequest));
            }
        }

        @Override
        public Boolean deleteNewsById(Long id) {
            verification.verificationNewsId(id);
            if (newsRepository.checkNewsById(id)) {
                return newsRepository.deleteNewsById(id);
            } else {
                throw new NewsNotFoundException(String.format(NewsNotFoundException.getCode(),id));
            }
        }
    }