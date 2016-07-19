package com.vlad.pet.contactlist.model.service;

import com.vlad.pet.contactlist.model.Contact;
import com.vlad.pet.contactlist.model.dao.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ContactDao contactDao;
    @Autowired
    public ContactService(ContactDao contactDao) {
        if (contactDao == null) {
            throw new NullPointerException("ContactDao is null");
        }
        this.contactDao = contactDao;
    }
    public void save(Contact contact) {
        contactDao.persist(contact);
    }

    public void delete(Contact contact) {
        contactDao.remove(contact);
    }

    public void update(Contact contact) {
        contactDao.merge(contact);
    }

    public List<Contact> search(String needle) {
        if (needle.isEmpty()) {
            throw new IllegalArgumentException("String is empty");
        }
        return contactDao.find(needle);
    }
    public Contact findById(Long id) {
        return contactDao.findById(id);
    }
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
