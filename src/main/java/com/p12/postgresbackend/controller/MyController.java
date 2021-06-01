package com.p12.postgresbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import com.p12.postgresbackend.model.Contact;
import com.p12.postgresbackend.service.IContactService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @Autowired
    private IContactService contactService;

    @GetMapping("/showContacts")
    public String findContacts(Model model) {

        var contacts = (List<Contact>) contactService.findAll();

        model.addAttribute("contacts", contacts);

        return "showContacts";
    }
}
