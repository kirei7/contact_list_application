package com.vlad.pet.facebook;

import com.vlad.pet.facebook.dao.ContactDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ContactDaoTest {


    @Autowired
    ContactDao contactDao;

    @Test
    @Transactional
    @Rollback(true)
    public void persistGivenContact() {
        Contact contact = getTestContact();
        contactDao.persist(contact);
        assertEquals(contact.getFirstName(), contactDao.findById(contact.getId()).getFirstName());
    }

    @Test
    @Transactional
    @Rollback(true)
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
    @Rollback(true)
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
    @Rollback(true)
    public void searchByName() {
        List<String> lastNames = new ArrayList<String>();
        lastNames.add("Holmes");
        lastNames.add("Reagan");
        lastNames.add("McDonald");
        lastNames.add("Jenkees");
        lastNames.add("Jenkins");
        String nameToSearch= "Ronald";
        List<Contact> contacts = new ArrayList<Contact>();
        for (String lastName : lastNames) {
            Contact temp = getTestContact();
            temp.setFirstName(nameToSearch);
            temp.setLastName(lastName);
            contacts.add(temp);
        }
        int length = lastNames.size();
        contacts.get(0).setFirstName("Sherlock");
        contacts.get(--length).setFirstName("Karl");

        List<Contact> finded = contactDao.find(nameToSearch);
        for (Contact contact : finded){}
            //log.debug("contact name: " + contact.getLastName());
        //assertTrue(contact.getFirstName().equals(nameToSearch));
        //number of contacts with name "Ronald" must be 3
        //assertTrue(finded.size() == 3);

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
