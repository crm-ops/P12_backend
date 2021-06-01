package com.p12.postgresbackend.service;



import com.p12.postgresbackend.model.Contact;

import java.util.List;

public interface IContactService {

    List<Contact> findAll();
}
