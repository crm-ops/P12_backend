package com.p12.postgresbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p12.postgresbackend.service.ContactService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.p12.postgresbackend.model.Contact;
import com.p12.postgresbackend.service.IContactService;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping("/showContacts") // test ok
    public String showContacts(Model model) {

        var contacts = (List<Contact>) contactService.findAll();

        model.addAttribute("contacts", contacts);

        return "showContacts";
    }


    @GetMapping("/getContacts") //test ok
    @ResponseBody
    public List<Contact> getContacts(Model model) throws JSONException {

        var contacts = (List<Contact>) contactService.findAll();
        return contacts;
    }


    @GetMapping("/getContactByEmail") //test ok
    @ResponseBody
    public Contact getContactByEmail(@RequestParam(value = "email", defaultValue = "john@doe.com", required=false) String email) throws JSONException {

        var contact = (Contact) contactService.getContactByEmail(email);
        return contact;
    }


    @GetMapping("/getContactByIntegrationEmail") //test ok
    @ResponseBody
    public Contact getContactByIntegrationEmail(@RequestParam(value = "integrationemail", defaultValue = "john@doe.com", required=false) String integrationemail) throws JSONException {

        var contact = (Contact) contactService.getContactByIntegrationemail(integrationemail);
        return contact;
    }


    @GetMapping("/getContactById")
    @ResponseBody
    public Contact getContactById(@RequestParam(value = "id", defaultValue = "0", required=false) Long id) throws JSONException {
            Contact con = new Contact();

            var val = contactService.findById(id);
            if(val.isPresent()) {
                System.out.println("Content of val >"+val.get());
                con.setId(val.get().getId());
                con.setFirstname(val.get().getFirstname());
                con.setEmail(val.get().getEmail());
                con.setMailingcity(val.get().getMailingcity());
                con.setAccountid(val.get().getAccountid());
                con.setLastame(val.get().getLastname());
                con.setIntegrationemail(val.get().getIntegrationemail());
                con.setSfid(val.get().getSfId());
            } else {
                con.setId(Long.valueOf("-1"));
                con.setFirstname("notfound");
                con.setEmail("notfound");
                con.setMailingcity("notfound");
                con.setAccountid("notfound");
                con.setLastame("notfound");
                con.setIntegrationemail("notfound");
                con.setSfid("notfound");

            }



    return con;
    }


    @GetMapping("/getContactBySfid") //
    @ResponseBody
    public Contact getContactBySfid(@RequestParam(value = "sfid", defaultValue = "0", required = false) String sfid) throws JSONException {

        var contact = (Contact) contactService.getContactBySfid(sfid);
        return contact;
    }



    @PostMapping("/insertContact")  //test ok returning Id
    @ResponseBody
    public String insertContact(@RequestBody String payload) throws JsonProcessingException, InterruptedException {

     //   HashMap<String, String> map = new HashMap<>();


        ObjectMapper mapper = new ObjectMapper();
        Contact tmpctc = mapper.readValue(payload.toString(), Contact.class);
        System.out.println("tmpcontact is > " + tmpctc.toString());

        Contact lookupcontactbyemail = contactService.getContactByEmail(tmpctc.getEmail());
        Contact lookupcontactbyintegrationemail = contactService.getContactByIntegrationemail(tmpctc.getIntegrationemail());
        String returnedContactId="";

        if (lookupcontactbyemail.getSfId() == "notfound" && lookupcontactbyintegrationemail.getSfId() == "notfound") {

           // map.put("integrationemail", integrationemail);
           // map.put("object", payload.toString());



            Contact finalctc = new Contact();
            finalctc.setEmail(tmpctc.getEmail());
            finalctc.setIntegrationemail(tmpctc.getIntegrationemail());
            finalctc.setFirstname(tmpctc.getFirstname());
            finalctc.setLastame(tmpctc.getLastname());
            finalctc.setMailingcity(tmpctc.getMailingcity());
            finalctc.setAccountid(tmpctc.getAccountid());

            System.out.println("Generated final contact from json is > " + finalctc.toString());

             returnedContactId=contactService.insertContact(finalctc);








        } else if (lookupcontactbyemail.getEmail()!="notfound") {

            System.out.println("contact email field was matched - sfid is  >" + lookupcontactbyemail.getSfId());

            returnedContactId=lookupcontactbyemail.getId().toString();

        } else if (lookupcontactbyintegrationemail.getIntegrationemail()!="notfound") {
            System.out.println("contact integrationemail field was matched - sfid is  >" + lookupcontactbyintegrationemail.getSfId());
            returnedContactId=lookupcontactbyintegrationemail.getId().toString();

        }

        returnedContactId = "{\"id\":\""+returnedContactId+"\"}";

        return  returnedContactId;
    }



//updateContact looks for a contact in Salesforce comparing request parameter integration_email__c with both salesforce standard email field and integration_email__c field
// if a match is found for either of those, the record content is replaced by the payload content according to the mapping - the sfid is returned to the caller
// if a match is not found a message is returned to the caller saying the record does not exist
    @PostMapping("/updateContact")
    @ResponseBody
    public String updateContact(@RequestParam(value = "integrationemail", defaultValue = "john@doe.com", required=false) String integrationemail, @RequestBody String payload) throws JsonProcessingException {
        // String payloadstr = payload.toString();

        Contact lookupcontactbyemail = contactService.getContactByEmail(integrationemail);
        Contact lookupcontactbyintegrationemail = contactService.getContactByIntegrationemail(integrationemail);
        String returnedContactId="";

        if (lookupcontactbyemail.getSfId() != "notfound" || lookupcontactbyintegrationemail.getSfId() != "notfound") {

            // map.put("integrationemail", integrationemail);
            // map.put("object", payload.toString());

            ObjectMapper mapper = new ObjectMapper();
            Contact tmpctc = mapper.readValue(payload.toString(), Contact.class);
            System.out.println("tmpcontact is > " + tmpctc.toString());

            Contact finalctc = new Contact();
            finalctc.setEmail(tmpctc.getEmail());
            finalctc.setIntegrationemail(tmpctc.getIntegrationemail());
            finalctc.setFirstname(tmpctc.getFirstname());
            finalctc.setLastame(tmpctc.getLastname());
            finalctc.setMailingcity(tmpctc.getMailingcity());
            finalctc.setAccountid(tmpctc.getAccountid());

            System.out.println("Generated final contact from json is > " + finalctc.toString());

            returnedContactId=contactService.updateContact(finalctc);


        } else if (lookupcontactbyemail.getEmail()=="notfound") {

            System.out.println("contact email field was not matched - sfid is  >" + lookupcontactbyemail.getSfId());

            returnedContactId="-1";

        } else if (lookupcontactbyintegrationemail.getIntegrationemail()=="notfound") {
            System.out.println("contact integrationemail field was matched - sfid is  >" + lookupcontactbyintegrationemail.getSfId());
            returnedContactId="-1";

        }


        returnedContactId = "{\"id\":\""+returnedContactId+"\"}";
        return  returnedContactId;
    }




    @GetMapping("/getContactSfidByemail") //test ok
    @ResponseBody
    public Map<String,String> getContactSfidByEmail(@RequestParam(value = "integrationemail", defaultValue = "john@doe.com", required=false) String integrationemail) {

        String sfid = contactService.getContactSfIdByEmail(integrationemail);

        Map<String, String> map = new HashMap<>();
        map.put("sfid", sfid);

        return map;
    }






    @GetMapping("/deleteContactByEmail")
    @ResponseBody
    public String deleteContactByEmail(@RequestParam(value = "integrationemail", defaultValue = "john@doe.com", required=false) String integrationemail) {

        String deletedContact = contactService.deleteContact(integrationemail);


        return deletedContact;
    }










}
