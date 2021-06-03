package com.p12.postgresbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p12.postgresbackend.model.Contract;
import com.p12.postgresbackend.service.IContractService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ContractController {

    @Autowired
    private IContractService ContractService;

    @GetMapping("/showContracts")
    public String showContracts(Model model) {

        var contracts = (List<Contract>) ContractService.findAll();

        model.addAttribute("contracts", contracts);

        return "showContracts";
    }


    @GetMapping("/getContracts")
    @ResponseBody
    public List<Contract> getContracts(Model model) throws JSONException {

        var Contracts = (List<Contract>) ContractService.findAll();
        return Contracts;
    }


    @PostMapping("/saveContract")
    @ResponseBody
    public Map<String,String> saveContract(@RequestParam(value = "integrationcontractid__c", defaultValue = "john@doe.com") String integrationcontractid__c, @RequestBody String payload) throws JsonProcessingException, InterruptedException {
        // String payloadstr = payload.toString();
        System.out.println("request integrationcontractid__c value "+ integrationcontractid__c);
        HashMap<String, String> map = new HashMap<>();
        map.put("integrationcontractid__c", integrationcontractid__c);
        map.put("object", payload.toString());
        System.out.println("payloadtostring is > "+payload.toString());
        ObjectMapper mapper = new ObjectMapper();
        Contract tmpctc = mapper.readValue(payload.toString(), Contract.class);

        Contract finalctc= new Contract();
        finalctc.setStatus(tmpctc.getStatus());
        finalctc.setStartdate(tmpctc.getStartdate());
        finalctc.setSpecialterms(tmpctc.getSpecialterms());
        finalctc.setDescription(tmpctc.getDescription());
        finalctc.setContractterm(tmpctc.getContractterm());
        finalctc.setAccountid(tmpctc.getAccountid());
        finalctc.setintegrationcontractid(integrationcontractid__c);



        System.out.println("Generated Contract from json is > "+finalctc.toString());

        String savedContractSfid=ContractService.saveContract(finalctc);

        System.out.println("Saved ContractId is >"+ savedContractSfid);
        map.put("ContractSfId",savedContractSfid);

        return map;
    }




    @GetMapping("/getContractSfidByIntegrationcontractid")
    @ResponseBody
    public Map<String,String> getContractByEmail(@RequestParam(value = "integrationcontractid__c", defaultValue = "john@doe.com", required=false) String integrationcontractid__c) {

        String sfid = ContractService.getContractSfId(integrationcontractid__c);

        Map<String, String> map = new HashMap<>();
        map.put("sfid", sfid);

        return map;
    }


    @GetMapping("/deleteContractByintegrationcontractid")
    @ResponseBody
    public Map<String,String> deleteContractByintegrationcontractid(@RequestParam(value = "integrationcontractid__c", defaultValue = "john@doe.com", required=true) String integrationcontractid__c) {

        Map<String,String> deletedContract = ContractService.deleteContract(integrationcontractid__c);


        return deletedContract;
    }




    //updateOrInsertContract
    //lookup the Contract in salesforce
        //if email exist and all other fields are the same > return Salesforce Id to calling app

        //if email exist AXG Contract fields are different and not null > update SF and return Id

        //if AXG Contract email does not exist > insert Contract and return id







}
