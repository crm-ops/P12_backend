package com.p12.postgresbackend.service;



import com.p12.postgresbackend.model.Contact;

import java.util.List;
import java.util.Map;

public interface IContactService {

    List<Contact> findAll();

    String saveContact(Contact contact);


    String getContactSfId(String email);

    Map<String,String> deleteContact(String email);

}
