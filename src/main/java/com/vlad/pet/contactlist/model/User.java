package com.vlad.pet.contactlist.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    @Size(min = 5, max = 16)
    private String nickName;

    @OneToMany( targetEntity=Contact.class, fetch = FetchType.EAGER)
    private Set<Contact> contactList = new HashSet<>();

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
        int l = nickName.length();
        if (l < 5 || l > 16) throw new IllegalArgumentException("Nickname length must be more than 5 and less than 16");
        this.nickName = nickName;
    }

    public Set<Contact> getContactList() {
        return new HashSet<>(contactList);
    }

    public void setContactList(Set<Contact> contactList) {
        this.contactList = contactList;
    }

    public void addContactToList(Contact contact) {
        if (!contactList.contains(contact)) {
            contactList.add(contact);
        }
    }
    public void removeContactFromList(Contact contact) {
        if (contactList.contains(contact)) {
            contactList.remove(contact);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "nickName='" + nickName + '\'' +
                ", id=" + id +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getId().equals(user.getId());

    }

    @Override
    public int hashCode() {
        Long base = 4l;
        if (getId() != null) {
            base += getId();
        }
        base += 17;
        return Long.hashCode(base);
    }
}
