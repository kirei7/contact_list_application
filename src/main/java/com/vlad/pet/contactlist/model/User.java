package com.vlad.pet.contactlist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany( targetEntity=Contact.class )
    private List contactList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getContactList() {
        return new ArrayList(contactList);
    }

    public void setContactList(List contactList) {
        this.contactList = contactList;
    }
}
