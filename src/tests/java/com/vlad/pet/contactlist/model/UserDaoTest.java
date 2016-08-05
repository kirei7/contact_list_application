package com.vlad.pet.contactlist.model;

import com.vlad.pet.contactlist.model.dao.ContactDao;
import com.vlad.pet.contactlist.model.dao.UserDao;
import com.vlad.pet.contactlist.model.user.User;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    private static final Logger logger = LoggerFactory.getLogger("debug");

    @Autowired
    private ContactDao contactDao;
    @Autowired
    private UserDao userDao;

    //also checks if
    //1) user is persisted
    //2) user is deleted
    //3) there is no need to create new
    //empty set to bind contacts to user
    @Test
    @Transactional
    @Rollback
    public void findUserByNickName() {
        Contact contact = new Contact();
        contact.setFirstName("Uncle Bob");
        contactDao.persist(contact);
        String name = "nickName";
        User user = createUser().withNickName(name).withPasswordHash(name);
        user.setNickName(name);
        //3)
        user.addContactToList(contact);
        //1)
        userDao.persist(user);
        User finded = userDao.find(name);
        assertEquals(user, finded);
        //check if contact lists is also equals
        assertEquals(user.getContactList(), finded.getContactList());
        //2)
        userDao.removeById(user.getId());
        assertNull(userDao.find(name));
    }

    @Test
    @Transactional
    @Rollback
    public void allUsersIsStoredAndRetrieved() {
        Set<User> users = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            User user = createUser();
            userDao.persist(user
                    .withNickName(
                            Integer.toString(i) +
                            Integer.toString(i) +
                            Integer.toString(i) +
                            Integer.toString(i) +
                            Integer.toString(i))
                    .withPasswordHash(
                            Integer.toString(i) +
                            Integer.toString(i) +
                            Integer.toString(i) +
                            Integer.toString(i) +
                            Integer.toString(i))
            );
            users.add(user);
        }
        assertEquals(users, userDao.getAllUsers());
    }

    /*@Test
    @Transactional
    @Rollback
    public void badNickNameLegthThrowsIAE() {
        int exceptionsThrown = 0;
        List<String> names = getIllegalNickNames();
        for (String name : names) {
            try {
                assignNameAndPasswordHashAndPersist(new User(), name, name);
            } catch (IllegalArgumentException ex) {
                exceptionsThrown++;
            }
        }
        int expectedExceptions = names.size();
        assertEquals(expectedExceptions, exceptionsThrown);
    }*/

    @Test
    public void findUnexistingUserReturnNull() {
        //unexistent id
        User user = userDao.find(4l);
        assertNull(user);
        //unexistent nickname
        user = userDao.find("name");
        assertNull(user);
    }

    private User createUser() {
        return new User();
    }
    private void assignNameAndPasswordHashAndPersist(User user, String name, String passwordHash) {
        user.setNickName(name);
        user.setPasswordHash(passwordHash);
        userDao.persist(user);
    }
    private List<String> getIllegalNickNames() {
        List<String> list = new ArrayList<>();
        list.add("less");
        list.add("too_long_nickname");
        return list;
    }
}
