package com.socialmedia.util;

import com.socialmedia.model.User;
import java.io.*;
import java.util.List;
import java.util.Optional;

// Класс-эксперт для работы с файлами
public class FileManager {
    // Метод для сохранения данных в файл
    public boolean saveToFile(String filename, List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(users);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для загрузки данных из файла
    @SuppressWarnings("unchecked")
    public Optional<List<User>> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            return Optional.of((List<User>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
