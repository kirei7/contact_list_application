package com.vlad.pet.contactlist.model.dao;

import com.vlad.pet.contactlist.model.User;

import java.util.Set;

public interface UserDao {
    void persist(User contact);

    User remove(User contact);

    User removeById(Long id);

    void merge(User contact);

    User findById(Long id);

    User findByNickName(String needle);

    Set<User> getAllUsers();
}
