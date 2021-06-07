package com.p12.postgresbackend.service;



import com.p12.postgresbackend.model.Contact;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IContactService {

    List<Contact> findAll();

    String insertContact(Contact contact);

    String updateContact(Contact contact);


    String getContactSfIdByEmail(String email);

    String deleteContact(String email);

    Contact getContactByEmail(String email);


    Contact getContactByIntegrationemail(String email);


    Contact getContactBySfid( String sfid);

    Optional<Contact> findById(Long id);
}
