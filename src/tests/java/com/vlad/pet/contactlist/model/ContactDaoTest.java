package com.vlad.pet.contactlist.model;

import com.vlad.pet.contactlist.model.dao.ContactDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ContactDaoTest {

    private static final Logger logger = LoggerFactory.getLogger("debug");

    @Autowired
    private ContactDao contactDao;

    @Test
    @Transactional
    @Rollback
    public void persistGivenContact() {
        Contact contact = getTestContact();
        contactDao.persist(contact);
        assertEquals(contact.getFirstName(), contactDao.findById(contact.getId()).getFirstName());
    }

    @Test
    @Transactional
    @Rollback
    public void findPersistedContact() {
        Contact contact = getTestContact();
        contactDao.persist(contact);
        //find by entity
        assertEquals(contact.getLastName(), contactDao.find(contact.getFirstName()).get(0).getLastName());
        //find by id
        assertEquals(contact, contactDao.findById(contact.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void deletePersistedContact() {
        Contact contact1 = getTestContact();
        Contact contact2 = getTestContact();
        contactDao.persist(contact1);
        contactDao.persist(contact2);
        contactDao.remove(contact1);
        contactDao.removeById(contact2.getId());
        assertNull(contactDao.findById(contact1.getId()));
        assertNull(contactDao.findById(contact2.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void searchByName() {
        List<String> lastNames = new ArrayList<>();
        lastNames.add("Holmes");
        lastNames.add("Reagan");
        lastNames.add("McDonald");
        lastNames.add("Jenkees");
        lastNames.add("Jenkins");
        String nameToSearch= "Ronald";
        List<Contact> contacts = new ArrayList<>();
        for (String lastName : lastNames) {
            Contact temp = getTestContact();
            temp.setFirstName(nameToSearch);
            temp.setLastName(lastName);
            contacts.add(temp);
        }
        int length = lastNames.size();
        contacts.get(0).setFirstName("Sherlock");
        contacts.get(--length).setFirstName("Karl");
        for (Contact contact : contacts) {
            contactDao.persist(contact);
        }
        List<Contact> finded = contactDao.find("ron");
        for (Contact contact : finded) {
            assertTrue(contact.getFirstName().equals(nameToSearch));
        }
        //number of contacts with name "Ronald" must be 3
        assertTrue(finded.size() == 3);
    }

    @Test
    @Transactional
    @Rollback
    public void getAllContactsReturnOrderedList() {
        List<String> firstNames = new ArrayList<>();
        firstNames.add("Atticus");
        firstNames.add("James");
        firstNames.add("Indiana");
        firstNames.add("Rick");
        firstNames.add("Will");
        firstNames.add("Lily");
        firstNames.add("Bane");
        List<String> lastNames = new ArrayList<>();
        lastNames.add("Finch");
        lastNames.add("Bond");
        lastNames.add("Jones");
        lastNames.add("Blaine");
        lastNames.add("Kane");
        lastNames.add("Bond");

        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < firstNames.size(); i++) {
            Contact temp = new Contact();
            temp.setFirstName(firstNames.get(i));
            if (i < lastNames.size())
            temp.setLastName(lastNames.get(i));
            contacts.add(temp);
            contactDao.persist(temp);
        }
        Collections.sort(contacts);
        List<Contact> persisted = contactDao.getAllContacts();
        for (int i = 0; i < contacts.size(); i++) {
            assertEquals(contacts.get(i), persisted.get(i));
        }
    }
    //returns a valid Contact entity
    private Contact getTestContact() {
        Contact contact = new Contact();
        contact.setFirstName("Vlad");
        contact.setLastName("Sereda");
        contact.setEmail("vlad.sereda.y@gmail.com");
        contact.setMobileNum("+380687044854");
        contact.setWorkNum("322223");
        return contact;
    }
}
