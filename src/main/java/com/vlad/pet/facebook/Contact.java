package com.vlad.pet.facebook;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Contact  implements Comparable<Contact> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NotNull
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNum;
    private String workNum;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public String getWorkNum() {
        return workNum;
    }

    public String getEmail() {
        return email;
    }

    public int compareTo(Contact o) {
        return this.getFirstName().compareTo(o.getFirstName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return getId() == contact.getId();
    }

    @Override
    public int hashCode() {
        Long base = 0l;
        if (getId() != null) {
            base += getId();
        }
        base += 4;
        return Long.hashCode(base);
    }
}
