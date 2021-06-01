package com.p12.postgresbackend.service;



import com.p12.postgresbackend.model.Contact;
import com.p12.postgresbackend.repository.ContactRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public List<Contact> findAll() {

        var contacts = (List<Contact>) repository.findAll();

        return contacts;
    }
}