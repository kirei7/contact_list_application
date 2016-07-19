package com.vlad.pet.contactlist.model;

import com.vlad.pet.contactlist.model.exception.UserAlreadyRegisteredException;
import com.vlad.pet.contactlist.model.service.ContactService;
import com.vlad.pet.contactlist.model.service.UserService;
import com.vlad.pet.contactlist.model.user.User;
import com.vlad.pet.contactlist.model.user.UserForm;
import com.vlad.pet.contactlist.model.util.OwnPasswordEncoder;
import com.vlad.pet.contactlist.model.util.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ApplicationManager {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private OwnPasswordEncoder encoder;
    @Autowired
    private UserFormValidator validator;

    public User registerUser(UserForm userForm) {
        if (userService.findByNickName(userForm.getNickName()) != null)
            throw new UserAlreadyRegisteredException("User with this name already exists: " + userForm.getNickName());
        validator.isValid(userForm);
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
