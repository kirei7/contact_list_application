package com.vlad.pet.contactlist.model.service;

import com.vlad.pet.contactlist.model.User;
import com.vlad.pet.contactlist.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    public UserService(UserDao userDao) {
        if (userDao == null) {
            throw new NullPointerException("UserDao is null");
        }
        this.userDao = userDao;
    }
    public void save(User user) {
        userDao.persist(user);
    }

    public void delete(User user) {
        userDao.remove(user);
    }

    public void update(User user) {
        userDao.merge(user);
    }

    public User findByNickName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("String is empty");
        }
        return userDao.find(name);
    }
    public User findByID(Long id) {
        return userDao.find(id);
    }
    public Set<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
