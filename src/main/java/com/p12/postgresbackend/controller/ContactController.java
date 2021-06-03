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

    @GetMapping("/showContacts")
    public String showContacts(Model model) {

        var contacts = (List<Contact>) contactService.findAll();

        model.addAttribute("contacts", contacts);

        return "showContacts";
    }


    @GetMapping("/getContacts")
    @ResponseBody
    public List<Contact> getContacts(Model model) throws JSONException {

        var contacts = (List<Contact>) contactService.findAll();
        return contacts;
    }


    @PostMapping("/saveContact")
    @ResponseBody
    public Map<String,String> saveContact(@RequestParam(value = "integration_email__c", defaultValue = "john@doe.com", required=false) String integration_email__c, @RequestBody String payload) throws JsonProcessingException {
        // String payloadstr = payload.toString();
        HashMap<String, String> map = new HashMap<>();
        map.put("integration_email__c", integration_email__c);
        map.put("object", payload.toString());
        System.out.println("payloadtostring is > "+payload.toString());
        ObjectMapper mapper = new ObjectMapper();
        Contact tmpctc = mapper.readValue(payload.toString(), Contact.class);

        Contact finalctc= new Contact();
        finalctc.setEmail(tmpctc.getEmail());
        finalctc.setIntegration_email__c(tmpctc.getEmail());
        finalctc.setFirstname(tmpctc.getFirstname());
        finalctc.setLastame(tmpctc.getLastname());
        finalctc.setMailingcity(tmpctc.getMailingcity());
        finalctc.setAccountid(tmpctc.getAccountid());
        finalctc.setIsDeleted(false);

        System.out.println("Generated contact from json is > "+finalctc.toString());

        String savedContactSfid=contactService.saveContact(finalctc);

        System.out.println("Saved contactId is >"+ savedContactSfid);
        map.put("contactSfId",savedContactSfid);

        return map;
    }




    @GetMapping("/getContactSfidByemail")
    @ResponseBody
    public Map<String,String> getContactByEmail(@RequestParam(value = "integration_email__c", defaultValue = "john@doe.com", required=false) String integration_email__c) {

        String sfid = contactService.getContactSfId(integration_email__c);

        Map<String, String> map = new HashMap<>();
        map.put("sfid", sfid);

        return map;
    }


    @GetMapping("/deleteContactByEmail")
    @ResponseBody
    public Map<String,String> softdeleteContactByEmail(@RequestParam(value = "integration_email__c", defaultValue = "john@doe.com", required=false) String integration_email__c) {

        Map<String,String> deletedContact = contactService.deleteContact(integration_email__c);


        return deletedContact;
    }




    //updateOrInsertContact
    //lookup the contact in salesforce
        //if email exist and all other fields are the same > return Salesforce Id to calling app

        //if email exist AXG Contact fields are different and not null > update SF and return Id

        //if AXG Contact email does not exist > insert contact and return id







}
