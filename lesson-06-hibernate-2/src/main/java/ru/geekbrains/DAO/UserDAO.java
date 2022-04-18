package ru.geekbrains.DAO;

import ru.geekbrains.model.User;
import java.util.List;

public interface UserDAO {
    User findById(Long id);
    List<User> findAll();
    User saveOrUpdate(User user);
    void deleteById(Long id);
}
