package com.vlad.pet.contactlist.model;

import com.vlad.pet.contactlist.model.user.User;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;

public class UserTest {
    //getContactList() method should return not
    // an original list but it's copy
    @Test
    public void defensiveCopyingShouldBeUsed() {
        User user = new User();
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new Contact());
        user.setContactList(contacts);
        assertFalse(contacts == user.getContactList());
    }

}
