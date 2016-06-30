package com.vlad.pet.contactlist.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String nickName;

    @OneToMany( targetEntity=Contact.class )
    private List contactList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List getContactList() {
        return new ArrayList(contactList);
    }

    public void setContactList(List contactList) {
        this.contactList = contactList;
    }
}
