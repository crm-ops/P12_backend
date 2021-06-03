package com.p12.postgresbackend.service;



import com.p12.postgresbackend.model.Contact;
import com.p12.postgresbackend.repository.ContactRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    public String saveContact(Contact contact) {


        Contact foundContact;
        String returnedSfid;

        if (repository.findByEmail(contact.getEmail()) == null) {

            System.out.println("No Existing contact in DB with email > " + contact.getEmail() + " ... inserting new record");
            Contact savedcontact = repository.save(contact);

            savedcontact = repository.findByEmail(contact.getEmail());

            System.out.println("Retrieved saved contact from DB with email > " + savedcontact.getEmail() + " \n");
            System.out.println("Retrieved saved contact from DB with sfid > " + savedcontact.getSfId());

            returnedSfid = savedcontact.getSfId();


        } else {

            foundContact = repository.findByEmail(contact.getEmail());
            System.out.println("Existing contact in DB with email > " + contact.getEmail() + " and with sfid " + foundContact.getSfId());
            foundContact.setFirstname(contact.getFirstname());
            foundContact.setLastame(contact.getLastname());
            foundContact.setEmail(contact.getIntegration_email__c());
            foundContact.setIntegration_email__c(contact.getIntegration_email__c());
            foundContact.setMailingcity(contact.getMailingcity());

            returnedSfid = foundContact.getSfId();

            repository.save(foundContact);


        }

        return returnedSfid;
    }


    @Override
    public String getContactSfId(String email) {

        String sfid;

        if (repository.findByEmail(email) == null) {

            sfid = "contact inexistant or not synchronized yet";

        } else {

            sfid = repository.findByEmail(email).getSfId();


        }

        return sfid;


    }






    @Override
    public Map<String,String> deleteContact(String email) {

        Map<String,String> deletedContact = new HashMap<>();

        if (repository.findByEmail(email) == null) {

            deletedContact.put(email, "not found");

        } else {
            Contact contacttodel = repository.findByEmail(email);

            repository.delete(contacttodel);

            deletedContact.put(email,"deleted in heroku db");

        }

        return deletedContact;


    }
}