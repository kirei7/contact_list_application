package com.vlad.pet.contactlist.model.util;

import com.vlad.pet.contactlist.model.Contact;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.compareTo(o2);
    }
}
