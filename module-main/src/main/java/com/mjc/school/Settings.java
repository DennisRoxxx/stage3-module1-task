package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.impl.NewsControllerImpl;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import static com.mjc.school.service.Verification.checkUserId;

import java.util.Scanner;

public class Settings {

    private final NewsController<NewsDtoRequest, NewsDtoResponse> newsController = new NewsControllerImpl();

    public void menuCommand() {
        System.out.println(Commands.MENU);
    }

    public void getAllNewsCommand() {
        System.out.println(Commands.GET_ALL_NEWS);
        newsController.readAllNews().forEach(System.out::println);
    }

    public void getNewsByIdCommand(Scanner input) {
        System.out.println(Commands.GET_NEWS_BY_ID);
        System.out.println(Commands.ENTER_NEWS_ID);
        System.out.println(newsController.readNewsById(checkUserId(input)));
    }

    public void createNewsCommand(Scanner input) {
        NewsDtoRequest newsDtoRequest = null;
            try {
                System.out.println(Commands.CREATE_NEWS);
                System.out.println(Commands.ENTER_NEWS_TITLE);
                String title = input.nextLine();
                System.out.println(Commands.ENTER_NEWS_CONTENT);
                String content = input.nextLine();
                System.out.println(Commands.ENTER_AUTHOR_ID);
                Long authorId = checkUserId(input);
                newsDtoRequest = new NewsDtoRequest(null, title, content, authorId);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        System.out.println(newsController.createNews(newsDtoRequest));
    }

    public void updateNews(Scanner input) {
        NewsDtoRequest newsDtoRequest = null;
            try {
                System.out.println(Commands.UPDATE_NEWS);
                System.out.println(Commands.ENTER_NEWS_ID);
                Long newsId = checkUserId(input);
                System.out.println(Commands.ENTER_NEWS_TITLE);
                String title = input.nextLine();
                System.out.println(Commands.ENTER_NEWS_CONTENT);
                String content = input.nextLine();
                System.out.println(Commands.ENTER_AUTHOR_ID);
                Long authorId = checkUserId(input);
                newsDtoRequest = new NewsDtoRequest(newsId, title, content, authorId);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        System.out.println(newsController.updateNews(newsDtoRequest));
    }

    public void deleteNews(Scanner input) {
        System.out.println(Commands.REMOVE_NEWS);
        System.out.println(Commands.ENTER_NEWS_ID);
        System.out.println(newsController.deleteNewsById(checkUserId(input)));
    }


}
