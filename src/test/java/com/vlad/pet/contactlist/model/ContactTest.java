package com.vlad.pet.contactlist.model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ContactTest {


    @Test
    @Parameters(method = "getContactData")
    public void contactDataShouldBeSavedInObject(
            String firstName,
            String lastName,
            String mobileNum,
            String workNum,
            String email) {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setMobileNum(mobileNum);
        contact.setWorkNum(workNum);
        contact.setEmail(email);

        assertEquals(firstName, contact.getFirstName());
        assertEquals(lastName, contact.getLastName());
        assertEquals(mobileNum, contact.getMobileNum());
        assertEquals(workNum, contact.getWorkNum());
        assertEquals(email, contact.getEmail());
    }

    @Test
    public void contactsMustBeProperlyComparedFirstName() {
        List<String> names = new ArrayList<>();
        names.add("Владислав");
        names.add("Vlad");
        names.add("Gena");
        names.add("2D2R");
        List<Contact> contacts = new ArrayList<>();
        for (String name : names) {
            Contact contact = new Contact();
            contact.setFirstName(name);
            contacts.add(contact);
        }
        for (int i = 0; i < (names.size() - 1); i++) {
            assertEquals(
                    names.get(i).compareTo(names.get(i + 1)),
                    contacts.get(i).compareTo(contacts.get(i + 1))
            );
        }
    }

    private Object[] getContactData() {
        return $(
                $("Владислав", "Середа", "380687044854", "322223", "vlad.sereda.y@gmail.com"),
                $("Vladislav", "Sereda", "380687044854", "322223", "vlad.sereda.y@gmail.com"),
                $("Roland", "Deschain", "0019061999", "195799", "gunslinger@gileadmail.com")
        );
    }

}
