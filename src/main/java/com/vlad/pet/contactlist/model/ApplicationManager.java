package com.vlad.pet.contactlist.model;

import com.vlad.pet.contactlist.model.exception.UserAlreadyRegisteredException;
import com.vlad.pet.contactlist.model.service.ContactService;
import com.vlad.pet.contactlist.model.service.UserService;
import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.model.user.UserForm;
import com.vlad.pet.contactlist.model.util.OwnPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ApplicationManager {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private OwnPasswordEncoder encoder;

    @PostConstruct
    private void createDefaultUser() {
        registerUser(
                new UserForm().withNickName("default").withPassword("default")
        );
    }
    public User registerUser(UserForm userForm) {
        if (userService.findByNickName(userForm.getNickName()) != null)
            throw new UserAlreadyRegisteredException("User with this name already exists: " + userForm.getNickName());
        User user = new User()
                .withNickName(userForm.getNickName())
                .withPasswordHash(
                    encoder.encode(userForm.getPassword())
                );
        return userService.save(user);
    }
    public Contact addContactToUserList(User user, Contact contact) {
        contactService.save(contact);
        user.addContactToList(contact);
        userService.update(user);
        return contact;
    }

    public Contact removeContactFromUserList(User user, Contact contact) {
        user.removeContactFromList(contact);
        contactService.delete(contact);
        userService.update(user);
        return contact;
    }

    public Contact updateContactFromUserList(Contact contact) {
        return contactService.update(contact);
    }

    public List<Contact> getAllUserContacts(User user) {
        List<Contact> contacts = new ArrayList<>(user.getContactList());
        contacts.sort(null);
        return contacts;
    }
    public Set<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
