package com.socialmedia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Класс, представляющий пользователя
public class User implements Serializable {
    // Имя пользователя (уникальный идентификатор)
    private String username;
    // Список постов пользователя
    private List<Post> posts;

    // Конструктор класса
    public User(String username) {
        this.username = username;
        this.posts = new ArrayList<>();
    }

    // Геттер для username
    public String getUsername() {
        return username;
    }

    // Сеттер для username
    public void setUsername(String username) {
        this.username = username;
    }

    // Геттер для списка постов
    public List<Post> getPosts() {
        return posts;
    }

    // Сеттер для списка постов
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    // Метод для добавления поста
    public void addPost(Post post) {
        posts.add(post);
    }

    // Метод для удаления поста
    public void removePost(Post post) {
        posts.remove(post);
    }

    // Переопределение метода toString для удобного вывода информации о пользователе
    @Override
    public String toString() {
        return "User{username='" + username + "', posts=" + posts.size() + "}";
    }
}
