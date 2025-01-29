package com.socialmedia;

import com.socialmedia.controller.SocialMediaController;

// Главный класс приложения
public class Main {
    // Точка входа в приложение
    public static void main(String[] args) {
        // Создаем и запускаем контроллер
        SocialMediaController controller = new SocialMediaController();
        controller.run();
    }
}
