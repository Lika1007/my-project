package com.socialmedia.service;

import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import java.util.List;
import java.util.Optional;

// Класс-эксперт для работы с постами
public class PostService {
    // Ссылка на сервис пользователей
    private final UserService userService;

    // Конструктор класса
    public PostService(UserService userService) {
        this.userService = userService;
    }

    // Метод для создания нового поста
    public Optional<Post> createPost(String username, String content) {
        Optional<User> userOpt = userService.findUserByUsername(username);
        if (userOpt.isPresent()) {
            Post post = new Post(content, username);
            userOpt.get().addPost(post);
            return Optional.of(post);
        }
        return Optional.empty();
    }

    // Метод для удаления поста
    public boolean deletePost(String username, String content) {
        Optional<User> userOpt = userService.findUserByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPosts().removeIf(post -> 
                post.getContent().equals(content) && 
                post.getAuthorUsername().equals(username));
        }
        return false;
    }

    // Метод для получения всех постов пользователя
    public List<Post> getUserPosts(String username) {
        Optional<User> userOpt = userService.findUserByUsername(username);
        return userOpt.map(User::getPosts).orElse(List.of());
    }
}
