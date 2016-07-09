package com.vlad.pet.contactlist.model.dao;

import com.vlad.pet.contactlist.model.User;

import java.util.Set;

public interface UserDao {
    User persist(User user);

    User remove(User user);

    User removeById(Long id);

    void merge(User user);

    User find(Long id);

    User find(String name);

    Set<User> getAllUsers();
}
