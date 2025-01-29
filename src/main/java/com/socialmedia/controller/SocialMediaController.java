package com.socialmedia.controller;

import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import com.socialmedia.service.PostService;
import com.socialmedia.service.UserService;
import com.socialmedia.util.FileManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

// Класс-контроллер для управления взаимодействием с пользователем
public class SocialMediaController {
    // Сервис для работы с пользователями
    private final UserService userService;
    // Сервис для работы с постами
    private final PostService postService;
    // Менеджер для работы с файлами
    private final FileManager fileManager;
    // Сканер для чтения пользовательского ввода
    private final Scanner scanner;

    // Конструктор класса
    public SocialMediaController() {
        this.userService = new UserService();
        this.postService = new PostService(userService);
        this.fileManager = new FileManager();
        this.scanner = new Scanner(System.in);
    }

    // Метод для запуска приложения
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    findUser();
                    break;
                case 4:
                    showAllUsers();
                    break;
                case 5:
                    createPost();
                    break;
                case 6:
                    deletePost();
                    break;
                case 7:
                    showUserPosts();
                    break;
                case 8:
                    saveToFile();
                    break;
                case 9:
                    loadFromFile();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Метод для вывода меню
    private void printMenu() {
        System.out.println("\n=== Социальная сеть ===");
        System.out.println("1. Создать пользователя");
        System.out.println("2. Удалить пользователя");
        System.out.println("3. Найти пользователя");
        System.out.println("4. Показать всех пользователей");
        System.out.println("5. Создать пост");
        System.out.println("6. Удалить пост");
        System.out.println("7. Показать посты пользователя");
        System.out.println("8. Сохранить в файл");
        System.out.println("9. Загрузить из файла");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    // Метод для создания пользователя
    private void createUser() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        Optional<User> userOpt = userService.createUser(username);
        if (userOpt.isPresent()) {
            System.out.println("Пользователь создан: " + userOpt.get());
        } else {
            System.out.println("Пользователь с таким именем уже существует");
        }
    }

    // Метод для удаления пользователя
    private void deleteUser() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        if (userService.deleteUser(username)) {
            System.out.println("Пользователь удален");
        } else {
            System.out.println("Пользователь не найден");
        }
    }

    // Метод для поиска пользователя
    private void findUser() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        Optional<User> user = userService.findUserByUsername(username);
        user.ifPresentOrElse(
            u -> System.out.println("Найден пользователь: " + u),
            () -> System.out.println("Пользователь не найден")
        );
    }

    // Метод для отображения всех пользователей
    private void showAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Пользователей нет");
        } else {
            System.out.println("Список пользователей:");
            users.forEach(System.out::println);
        }
    }

    // Метод для создания поста
    private void createPost() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите содержание поста: ");
        String content = scanner.nextLine();

        Optional<Post> post = postService.createPost(username, content);
        post.ifPresentOrElse(
            p -> System.out.println("Пост создан: " + p),
            () -> System.out.println("Не удалось создать пост")
        );
    }

    // Метод для удаления поста
    private void deletePost() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите содержание поста: ");
        String content = scanner.nextLine();

        if (postService.deletePost(username, content)) {
            System.out.println("Пост удален");
        } else {
            System.out.println("Пост не найден");
        }
    }

    // Метод для отображения постов пользователя
    private void showUserPosts() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        List<Post> posts = postService.getUserPosts(username);
        
        if (posts.isEmpty()) {
            System.out.println("У пользователя нет постов");
        } else {
            System.out.println("Посты пользователя:");
            posts.forEach(System.out::println);
        }
    }

    // Метод для сохранения данных в файл
    private void saveToFile() {
        System.out.print("Введите имя файла: ");
        String filename = scanner.nextLine();
        if (fileManager.saveToFile(filename, userService.getAllUsers())) {
            System.out.println("Данные сохранены в файл");
        } else {
            System.out.println("Ошибка при сохранении данных");
        }
    }

    // Метод для загрузки данных из файла
    private void loadFromFile() {
        System.out.print("Введите имя файла: ");
        String filename = scanner.nextLine();
        Optional<List<User>> users = fileManager.loadFromFile(filename);
        
        if (users.isPresent()) {
            userService.setUsers(users.get());
            System.out.println("Данные загружены из файла");
        } else {
            System.out.println("Ошибка при загрузке данных");
        }
    }
}
