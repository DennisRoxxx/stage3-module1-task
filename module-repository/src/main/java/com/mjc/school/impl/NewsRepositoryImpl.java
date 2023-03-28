package com.mjc.school.impl;


import com.mjc.school.DataSource;
import com.mjc.school.model.NewsModel;
import com.mjc.school.repository.interfaces.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NewsRepositoryImpl implements Repository<NewsModel> {

    private final DataSource dataSource = new DataSource();

    @Override
    public List<NewsModel> readAllNews() {
        return dataSource.getNews();
    }

    @Override
    public Boolean checkNewsById(Long id) {
        return dataSource.getNews().stream()
                .anyMatch(news -> id.equals(news.getId()));
    }
    @Override
    public NewsModel readNewsById(Long id) {
        return dataSource.getNews().stream()
                .filter(news -> id.equals(news.getId()))
                .findFirst().get();
    }

    @Override
    public NewsModel createNews(NewsModel createNews) {
        List<NewsModel> news = dataSource.getNews();
        news.sort(Comparator.comparing(NewsModel::getId));
        if (!news.isEmpty()) {
            createNews.setId(news.get(news.size() - 1).getId() + 1);
        } else {
            createNews.setId(Long.valueOf(1));
        }
        news.add(createNews);
        return createNews;
    }

    @Override
    public NewsModel updateNews(NewsModel updateNews) {
        NewsModel news = this.readNewsById(updateNews.getId());
        news.setTitle(updateNews.getTitle());
        news.setContent(updateNews.getContent());
        news.setLastUpdateDate(updateNews.getLastUpdateDate());
        news.setAuthorId(updateNews.getAuthorId());
        return news;
    }

    @Override
    public Boolean deleteNewsById(Long id) {
        List<NewsModel> deleteNewsById = new ArrayList<>();
        deleteNewsById.add(readNewsById(id));
        return dataSource.getNews().removeAll(deleteNewsById);
    }
}