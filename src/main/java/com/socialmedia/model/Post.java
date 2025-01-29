package com.socialmedia.model;

import java.io.Serializable;
import java.time.LocalDateTime;

// Класс, представляющий пост
public class Post implements Serializable {
    // Содержание поста
    private String content;
    // Дата создания поста
    private LocalDateTime createdAt;
    // Имя автора поста
    private String authorUsername;

    // Конструктор класса
    public Post(String content, String authorUsername) {
        this.content = content;
        this.authorUsername = authorUsername;
        this.createdAt = LocalDateTime.now();
    }

    // Геттер для content
    public String getContent() {
        return content;
    }

    // Сеттер для content
    public void setContent(String content) {
        this.content = content;
    }

    // Геттер для createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Сеттер для createdAt
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Геттер для authorUsername
    public String getAuthorUsername() {
        return authorUsername;
    }

    // Сеттер для authorUsername
    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    // Переопределение метода toString для удобного вывода информации о посте
    @Override
    public String toString() {
        return "Post{content='" + content + "', createdAt=" + createdAt + ", author='" + authorUsername + "'}";
    }
}
