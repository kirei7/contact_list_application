package com.vlad.pet.contactlist.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class UserTest {
    //getContactList() method should return not
    // an original list but it's copy
    @Test
    public void defensiveCopyingShouldBeUsed() {
        User user = new User();
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        contacts.add(new Contact());
        contacts.add(new Contact());
        user.setContactList(contacts);
        assertFalse(contacts == user.getContactList());
    }


}
