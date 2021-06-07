package com.p12.postgresbackend.repository;


import com.p12.postgresbackend.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {


    Contact findByEmail(String email);


    Contact findByIntegrationemail(String integrationemail);

    Contact findBySfid(String sfid);





}







