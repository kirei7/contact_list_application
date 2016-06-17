package com.vlad.pet.facebook;

import com.vlad.pet.facebook.dao.ContactDao;
import com.vlad.pet.facebook.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    private ContactDao contactDao;
    private ContactService contactService;

    @Before
    public void setUp() {
        contactDao = Mockito.mock(ContactDao.class);
        when(contactDao.find(anyString())).thenReturn(new ArrayList<Contact>());
        contactService = new ContactService(contactDao);
    }

    //service have to save a single given contact
    //using dao persist method
    @Test
    public void invokeDaoPersistMethodOnSave() {
        Contact contact = new Contact();
        contactService.save(contact);
        verify(contactDao).persist(contact);
    }
    @Test
    public void invokeDaoRemoveMethodOnDelete() {
        Contact contact = new Contact();
        contactService.delete(contact);
        verify(contactDao).remove(contact);
    }
    @Test
    public void invokeDaoMergeMethodOnUpdate() {
        Contact contact = new Contact();
        contactService.update(contact);
        verify(contactDao).merge(contact);
    }

    @Test
    public void invokeDaoFindMethodOnSearch() {
        String needle = "go";
        contactService.search(needle);
        verify(contactDao).find(needle);
    }

    @Test
    public void searchMethodReturnsListOfContacts() {
        String needle = "go";
        assertTrue(contactService.search(needle) != null);
    }
    //throws an exception when String argument is empty
    @Test(expected = IllegalArgumentException.class)
    public void searchMethodThrowsIAE() {
        contactService.search("");
    }
    //throws null pointer exception if string is null
    @Test(expected = NullPointerException.class)
    public void searchMethodThrowsNPE() {
        contactService.search(null);
    }

}
