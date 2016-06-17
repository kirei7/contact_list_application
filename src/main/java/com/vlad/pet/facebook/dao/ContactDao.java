package com.vlad.pet.facebook.dao;

import com.vlad.pet.facebook.Contact;

import java.util.List;

public interface ContactDao {
    void persist(Contact contact);

    Contact remove(Contact contact);

    Contact removeById(Long id);

    void merge(Contact contact);

    Contact findById(Long id);

    List<Contact> find(String needle);


}