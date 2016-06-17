package com.vlad.pet.facebook;

import java.util.ArrayList;
import java.util.List;

public class TestTool {
    public static void main(String... args) {
        List<String> lastNames = new ArrayList<String>();
        lastNames.add("Holmes");
        lastNames.add("Reagan");
        lastNames.add("McDonald");
        lastNames.add("Jenkees");
        lastNames.add("Jenkins");
        List<Contact> contacts = new ArrayList<Contact>();
        for (String lastName : lastNames) {
            Contact temp = getTestContact();
            temp.setFirstName("Ronald");
            temp.setLastName(lastName);
            contacts.add(temp);
        }
        int length = lastNames.size();
        contacts.get(0).setFirstName("Sherlock");
        contacts.get(--length).setFirstName("Karl");

        for (Contact contact : contacts) {
            System.out.println(contact.getFirstName() + " " + contact.getLastName());
        }

    }

    private static Contact getTestContact() {
        Contact contact = new Contact();
        contact.setFirstName("Vlad");
        contact.setLastName("Sereda");
        contact.setEmail("vlad.sereda.y@gmail.com");
        contact.setMobileNum("+380687044854");
        contact.setWorkNum("322223");
        return contact;
    }
}
