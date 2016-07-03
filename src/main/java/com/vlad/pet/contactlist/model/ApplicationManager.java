package com.vlad.pet.contactlist.model;

import com.vlad.pet.contactlist.model.exception.UserAlreadyRegisteredException;
import com.vlad.pet.contactlist.model.service.ContactService;
import com.vlad.pet.contactlist.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ApplicationManager {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    public void registerUser(User user) {
        if (userService.findByNickName(user.getNickName()) != null)
            throw new UserAlreadyRegisteredException("User with this name already exists: " + user.getNickName());
        userService.save(user);
    }
    public void addContactToUserList(User user, Contact contact) {
        contactService.save(contact);
        user.addContactToList(contact);
        userService.update(user);
    }

    public void removeContactFromUserList(User user, Contact contact) {
        user.removeContactFromList(contact);
        contactService.delete(contact);
        userService.update(user);
    }

    public void updateContactFromUserList(Contact contact) {
        contactService.update(contact);
    }

    public Set<Contact> getAllUserContacts(User user) {
        return user.getContactList();
    }
    public Set<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
