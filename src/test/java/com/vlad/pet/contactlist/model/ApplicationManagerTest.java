package com.vlad.pet.contactlist.model;

import com.vlad.pet.contactlist.model.dao.ContactDao;
import com.vlad.pet.contactlist.model.dao.UserDao;
import com.vlad.pet.contactlist.model.exception.UserAlreadyRegisteredException;
import org.junit.Before;
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
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationManagerTest {

    private static final Logger logger = LoggerFactory.getLogger("debug");

    @Autowired
    private UserDao userDao;
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private ApplicationManager applicationManager;

    private List<User> users = new ArrayList<>();
    private List<Contact> contacts = new ArrayList<>();

    @Before
    public void setUp() {
        users.add( new User()
                .withNickName("primo")
                .withPasswordHash("123")
        );
        users.add( new User()
                .withNickName("SecondAndLast")
                .withPasswordHash("345")
        );
        contacts.add(new Contact()
                .withFirstName("John")
                .withLastName("Doe")
                .withEmail("some@mail.com")
                .withMobileNum("38088088888")
                .withWorkNum("354256")
        );
        contacts.add(new Contact()
                .withFirstName("Stephen")
                .withLastName("King")
                .withEmail("derry@.maine.us")
                .withMobileNum("3899001999")
                .withWorkNum("666")
        );
        contacts.add(new Contact()
                .withFirstName("Scarlett")
                .withLastName("O'Hara")
                .withEmail("gonewith@the.wind")
                .withMobileNum("123365478958")
                .withWorkNum("193639")
        );
        contacts.add(new Contact()
                .withFirstName("Darth")
                .withLastName("Vader")
                .withEmail("lord@star.im")
                .withMobileNum("888888888")
                .withWorkNum("000000")
        );
        for(User user : users) {
            userDao.persist(user);
        }
        for(Contact contact : contacts) {
            contactDao.persist(contact);
        }
    }
    @Test
    @Transactional
    @Rollback
    public void registerUser() {
        User user = new User().withNickName("nickName").withPasswordHash("passwordHash");

        applicationManager.registerUser(user);
        assertTrue(applicationManager.getAllUsers().contains(user));
    }
    @Test(expected = UserAlreadyRegisteredException.class)
    @Transactional
    @Rollback
    public void unableToRegisterUsersWithIdenticalNickNames() {
        applicationManager.registerUser(new User().withNickName("primo"));
    }
    @Test
    @Transactional
    @Rollback
    public void addAndRemoveContactFromList() {
        User user = users.get(0);
        Contact contact = contacts.get(0);
        applicationManager.addContactToUserList(
                user,
                contact
        );
        //assure that contact has been added to list
        assertTrue(user.getContactList().contains(contact));
        applicationManager.removeContactFromUserList(user, contact);
        //assure that contact has been removed from list
        assertFalse(user.getContactList().contains(contact));
    }
    @Test
    @Transactional
    @Rollback
    public void contactInListCanBeUpdated() {
        User user = users.get(0);
        Contact contact = contacts.get(0);
        String initialName = contact.getFirstName();
        String changedName = "changed";
        applicationManager.addContactToUserList(
                user,
                contact
        );
        contact.setFirstName(changedName);
        applicationManager.updateContactFromUserList(contact);
        Contact changed = new ArrayList<Contact>(
                applicationManager.getAllUserContacts(user)
        ).get(0);
        logger.debug(changed.toString());
        assertEquals(changedName, changed.getFirstName());
    }
}
