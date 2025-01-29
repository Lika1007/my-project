package com.socialmedia.service;

import com.socialmedia.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Класс-эксперт для работы с пользователями
public class UserService {
    // Список всех пользователей
    private List<User> users;

    // Конструктор класса
    public UserService() {
        this.users = new ArrayList<>();
    }

    // Метод для создания нового пользователя
    public Optional<User> createUser(String username) {
        // Проверяем, существует ли пользователь с таким именем
        if (findUserByUsername(username).isPresent()) {
            return Optional.empty();
        }
        User user = new User(username);
        users.add(user);
        return Optional.of(user);
    }

    // Метод для удаления пользователя
    public boolean deleteUser(String username) {
        return users.removeIf(user -> user.getUsername().equals(username));
    }

    // Метод для поиска пользователя по имени
    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    // Метод для получения списка всех пользователей
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // Метод для установки списка пользователей (используется при загрузке из файла)
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
