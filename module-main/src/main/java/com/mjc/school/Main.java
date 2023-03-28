package com.mjc.school;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Settings settings = new Settings();

    Scanner input = new Scanner(System.in);
        while (true) {
        try {
            settings.menuCommand();
            switch (input.nextLine()) {
                case "1" -> settings.getAllNewsCommand();
                case "2" -> settings.getNewsByIdCommand(input);
                case "3" -> settings.createNewsCommand(input);
                case "4" -> settings.updateNews(input);
                case "5" -> settings.deleteNews(input);
                case "0" -> System.exit(0);
                default -> System.out.println(Commands.COMMAND_NOT_FOUND);
            }
        } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
