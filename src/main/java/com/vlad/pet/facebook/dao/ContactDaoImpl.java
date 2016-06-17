package com.vlad.pet.facebook.dao;

import com.vlad.pet.facebook.Contact;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ContactDaoImpl implements ContactDao{
    @PersistenceContext
    private EntityManager manager;
    public void persist(Contact contact) {
        manager.persist(contact);
    }

    public Contact remove(Contact contact) {
        if (manager.contains(contact)) {
            manager.remove(contact);
        } else {
            Contact attached = findById(contact.getId());
            manager.remove(attached);
            return attached;
        }
        return contact;
    }
    public Contact removeById(Long id) {
        if (id == null) return null;
        Contact contact = manager.find(Contact.class, id);
        if (manager.contains(contact)) {
            manager.remove(contact);
        } else {
            Contact attached = findById(contact.getId());
            manager.remove(attached);
            return attached;
        }
        return contact;
    }
    public void merge(Contact contact) {

    }
    public Contact findById(Long id) {
        if (id == null) return null;
        return manager.find(Contact.class, id);
    }

    public List<Contact> find(String needle) {
        if (needle == null || needle.isEmpty()) return null;
        return manager.createQuery("SELECT c FROM Contact c WHERE c.firstName LIKE :name")
                .setParameter("name", needle)
                .getResultList();
    }


}