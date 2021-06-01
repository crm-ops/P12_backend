package com.p12.postgresbackend.repository;


import com.p12.postgresbackend.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

}







