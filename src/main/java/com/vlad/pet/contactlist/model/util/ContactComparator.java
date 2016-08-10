package com.vlad.pet.contactlist.model.util;

import com.vlad.pet.contactlist.model.Contact;
import org.apache.log4j.Logger;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {
    private static final Logger LOGGER = org.apache.log4j.Logger.getLogger("debug");

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.compareTo(o2);
    }
}
