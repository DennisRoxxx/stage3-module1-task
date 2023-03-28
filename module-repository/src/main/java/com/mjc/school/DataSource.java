package com.mjc.school;
import com.mjc.school.model.AuthorModel;
import com.mjc.school.model.NewsModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DataSource {
    Random random = new Random();
    private List<AuthorModel> authors;
    private List<NewsModel> news;

    public DataSource() {
        this.authors = getAuthorsFromFile();
        this.news = getNewsFromFile();
    }
    public LocalDateTime randomDate() {
        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        return today.minusHours(10000);
    }
    private List<AuthorModel> getAuthorsFromFile() {
        authors = new ArrayList<>();

        for (int index = 1; index <= 20; index++) {
            authors.add(new AuthorModel((long) index
                    , getTitleAndContentFromFile("author.txt")));
        }
        return authors;
    }
    public List<NewsModel> getNews() {
        return this.news;
    }

    private List<NewsModel> getNewsFromFile() {
        LocalDateTime dateField = randomDate();

        news = new ArrayList<>();
        for (int index = 1; index <= 20; index++) {
            news.add(new NewsModel((long) index
                    , getTitleAndContentFromFile("title.txt")
                    , getTitleAndContentFromFile("content.txt")
                    , dateField
                    , dateField
                    , authors.get(random.nextInt(authors.size())).getId()));
        }
        return news;
    }

    private String getTitleAndContentFromFile(String resourcesFile) {

      String dataFromFile = null;
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                        Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(resourcesFile))))) {

              int counter = 1;
                int lines =  new Random().nextInt(20);
              while ((counter != lines) && (dataFromFile = bufferedReader.readLine()) != null) {
                   counter++;
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dataFromFile;
    }
}
