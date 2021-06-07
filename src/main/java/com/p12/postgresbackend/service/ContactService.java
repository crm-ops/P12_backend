package com.p12.postgresbackend.service;



import com.p12.postgresbackend.model.Contact;
import com.p12.postgresbackend.repository.ContactRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Optional<Contact> findById(Long id) {

        return repository.findById(id);
    }


    @Override
    public String insertContact(Contact contact) {


        Contact foundContactByEmail;
        Contact foundContactByintegrationemail;
        String returnedId = "";

        if (repository.findByEmail(contact.getEmail()) == null && repository.findByIntegrationemail(contact.getIntegrationemail()) == null) {

            System.out.println("No Existing contact in DB with email > " + contact.getEmail() + " ... inserting new record");
            Contact savedcontact = repository.save(contact);


            System.out.println("Retrieved saved contact from DB with email > " + savedcontact.getEmail() + " \n");
            System.out.println("Retrieved saved contact from DB with sfid > " + savedcontact.getSfId());

            returnedId = savedcontact.getId().toString();


        } else {

            foundContactByEmail = repository.findByEmail(contact.getEmail());
            foundContactByintegrationemail = repository.findByIntegrationemail(contact.getIntegrationemail());

            if (foundContactByEmail.getSfId() != "notfound") {

                System.out.println("Existing contact in DB with email > " + foundContactByEmail.getEmail() + " and with sfid " + foundContactByEmail.getSfId());
                returnedId = foundContactByEmail.getId().toString();

            } else if (foundContactByintegrationemail.getSfId() != "notfound") {

                returnedId = foundContactByintegrationemail.getId().toString();
            }


        }

        return returnedId;


    }


    @Override
    public String updateContact(Contact contact) {


        Contact foundContactByEmail;
        Contact foundContactByintegrationemail;
        String returnedId = "";

        if (repository.findByEmail(contact.getEmail()) != null || repository.findByIntegrationemail(contact.getIntegrationemail()) != null) {

            Contact tomodifyCtc = new Contact();

            if(contact.getEmail()!=null){
                tomodifyCtc= repository.findByEmail(contact.getEmail());
            } else
            {
                tomodifyCtc= repository.findByIntegrationemail(contact.getIntegrationemail());
            }

            System.out.println("Existing contact in DB with email > " + contact.getEmail() +" or integration email > " +contact.getIntegrationemail()+ " ... updating record");


            contact.setId(tomodifyCtc.getId());

            Contact savedcontact = repository.save(contact);



            returnedId = savedcontact.getId().toString();


        } else {

            foundContactByEmail = repository.findByEmail(contact.getEmail());
            foundContactByintegrationemail = repository.findByIntegrationemail(contact.getIntegrationemail());

            if (foundContactByEmail.getSfId() == "notfound") {

                System.out.println("Contact not found in DB with email > " + foundContactByEmail.getEmail());
                returnedId = "-1";

            } else if (foundContactByintegrationemail.getSfId() == "notfound") {

                returnedId = "-1";
            }


        }

        return returnedId;


    }


    @Override
    public String getContactSfIdByEmail(String email) {


        String sfid = "";

        if (repository.findByEmail(email) == null && repository.findByIntegrationemail(email) == null) {

            sfid = "no Sfid matched in salesforce for either email >" + email;


        } else {


            System.out.println("value of repository.findByEmail(email).getSfId()>" + repository.findByEmail(email).toString());

            if (repository.findByEmail(email).getSfId() != null) {
                sfid = repository.findByEmail(email).getSfId();
            } else if (repository.findByIntegrationemail(email).getSfId() != null) {
                sfid = repository.findByIntegrationemail(email).getSfId();
            } else if (repository.findByEmail(email).getSfId() == "nofound") {
                sfid = "notfound";
            } else if (repository.findByIntegrationemail(email).getSfId() == "notfound") {
                sfid = "notfound";
            } else {
                sfid = "we have a functional problem houston";
            }


        }


        return sfid;


    }


    @Override
    public Contact getContactByEmail(String email) {

        Contact returnedContact = new Contact();

        if (repository.findByEmail(email) == null) {

            returnedContact.setAccountid("notfound");
            returnedContact.setMailingcity("notfound");
            returnedContact.setIntegrationemail("notfound");
            returnedContact.setSfid("notfound");
            returnedContact.setEmail("notfound");
            returnedContact.setFirstname("notfound");
            returnedContact.setLastame("notfound");

        } else {

            returnedContact = repository.findByEmail(email);


        }

        return returnedContact;


    }





        @Override
        public Contact getContactBySfid (String sfid){

            Contact returnedContact = new Contact();

            if (repository.findBySfid(sfid) == null) {

                returnedContact.setAccountid("notfound");
                returnedContact.setMailingcity("notfound");
                returnedContact.setIntegrationemail("notfound");
                returnedContact.setSfid("notfound");
                returnedContact.setEmail("notfound");
                returnedContact.setFirstname("notfound");
                returnedContact.setLastame("notfound");

            } else {

                returnedContact = repository.findBySfid(sfid);


            }

            return returnedContact;


        }


        @Override
        public Contact getContactByIntegrationemail (String integrationemail){

            Contact returnedContact = new Contact();

            if (repository.findByIntegrationemail(integrationemail) == null) {

                returnedContact.setAccountid("notfound");
                returnedContact.setMailingcity("notfound");
                returnedContact.setIntegrationemail("notfound");
                returnedContact.setSfid("notfound");
                returnedContact.setEmail("notfound");
                returnedContact.setFirstname("notfound");
                returnedContact.setLastame("notfound");

            } else {

                returnedContact = repository.findByIntegrationemail(integrationemail);


            }

            return returnedContact;


        }


        @Override
        public String deleteContact (String email){

            String deletedContact= "";

            if (repository.findByEmail(email) == null) {

                deletedContact="notFound";

            } else {
                Contact contacttodel = repository.findByEmail(email);

                repository.delete(contacttodel);

                deletedContact=contacttodel.getSfId();

            }

            return deletedContact;


        }
    }

