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

import java.util.*;

@Controller
public class ContractController {

    @Autowired
    private IContractService contractService;

    @GetMapping("/showContracts")
    public String showContracts(Model model) {

        var contracts = (List<Contract>) contractService.findAll();

        model.addAttribute("contracts", contracts);

        return "showContracts";
    }


    @GetMapping("/getContracts")
    @ResponseBody
    public List<Contract> getContracts(Model model) throws JSONException {

        var Contracts = (List<Contract>) contractService.findAll();
        return Contracts;
    }


    @GetMapping("/getContractById")
    @ResponseBody
    public Contract getContractById(@RequestParam(value = "id", defaultValue = "0", required=false) Long id) throws JSONException {
        Contract con = new Contract();

        var val = contractService.findById(id);
        if(val.isPresent()) {
            System.out.println("Content of val >"+val.get());
            con.setId(val.get().getId());
            con.setContractterm(val.get().getContractterm());
            con.setDescription(val.get().getDescription());
            con.setAccountid(val.get().getAccountid());
            con.setSpecialterms(val.get().getSpecialterms());
            con.setStartdate(val.get().getStartdate());
            con.setStatus(val.get().getStatus());
            con.setSfid(val.get().getSfid());
        } else {
            con.setId(Long.valueOf("-1"));
            con.setContractterm(Long.valueOf("-1"));
            con.setDescription("notfound");
            con.setAccountid("notfound");
            con.setStartdate(new Date());
            con.setSpecialterms("notfound");
            con.setStatus("notfound");
            con.setSfid("notfound");

        }



        return con;
    }



    @GetMapping("/getContractBySfid")
    @ResponseBody
    public Contract getContractBySfid(@RequestParam(value = "sfid", defaultValue = "XXXXXXX", required=false) String sfid) {

        Contract contract = contractService.getContractBySfid(sfid);


        return contract;
    }

    @GetMapping("/getContractByIntegrationcontractid")
    @ResponseBody
    public Contract getContractByIntegrationcontractid(@RequestParam(value = "integrationcontractid", defaultValue = "XXXXXXX", required=false) String integrationcontractid) {

        Contract contract = contractService.getContractByIntegrationcontractid(integrationcontractid);


        return contract;
    }


    @PostMapping("/insertContract")
    @ResponseBody
    public Map<String,String> insertContract(@RequestBody String payload) throws JsonProcessingException, InterruptedException {


        //does the contract exist in the db

        ObjectMapper mapper = new ObjectMapper();
        Contract tmpctc = mapper.readValue(payload.toString(), Contract.class);
        String  lookupsfid = contractService.getContractSfId(tmpctc.getIntegrationcontractid());

        // if lookup contract is null > save a new ontract

        // if not return sfid in the map

        // String payloadstr = payload.toString();

        HashMap<String, String> map = new HashMap<>();

        if (lookupsfid=="notfound") {
            System.out.println("request integrationcontractid value " + tmpctc.getIntegrationcontractid());


            map.put("integrationcontractid", tmpctc.getIntegrationcontractid());


            Contract finalctc = new Contract();
            finalctc.setStatus(tmpctc.getStatus());
            finalctc.setStartdate(tmpctc.getStartdate());
            finalctc.setSpecialterms(tmpctc.getSpecialterms());
            finalctc.setDescription(tmpctc.getDescription());
            finalctc.setContractterm(tmpctc.getContractterm());
            finalctc.setAccountid(tmpctc.getAccountid());
            finalctc.setintegrationcontractid(tmpctc.getIntegrationcontractid());


            System.out.println("Generated Contract from json is > " + finalctc.toString());

            String savedContractSfid = contractService.saveContract(finalctc);
            Contract savedcontactobject= getContractByIntegrationcontractid(tmpctc.getIntegrationcontractid());

            System.out.println("Saved ContractId is >" + savedContractSfid);

            map.put("ContractSfId", savedContractSfid);
            map.put("HerokuId", savedcontactobject.getId().toString());

        }   else {
            map.put("ContractSfId ", lookupsfid +" exist");
        }

        return map;
    }




    @PostMapping("/updateContract")
    @ResponseBody
    public Map<String,String> updateContract(@RequestParam(value = "integrationcontractid", defaultValue = "john@doe.com") String integrationcontractid, @RequestBody String payload) throws JsonProcessingException, InterruptedException {
        // String payloadstr = payload.toString();
        System.out.println("request integrationcontractid value "+ integrationcontractid);
        HashMap<String, String> map = new HashMap<>();
        String  lookupsfid = contractService.getContractSfId(integrationcontractid);

        if (lookupsfid!=null) {
            System.out.println("request integrationcontractid__c value " + integrationcontractid);

            map.put("integrationcontractid", integrationcontractid);
         //
            System.out.println("payloadtostring is > " + payload.toString());
            ObjectMapper mapper = new ObjectMapper();
            Contract tmpctc = mapper.readValue(payload.toString(), Contract.class);

            Contract finalctc = new Contract();
            finalctc.setStatus(tmpctc.getStatus());
            finalctc.setStartdate(tmpctc.getStartdate());
            finalctc.setSpecialterms(tmpctc.getSpecialterms());
            finalctc.setDescription(tmpctc.getDescription());
            finalctc.setContractterm(tmpctc.getContractterm());
            finalctc.setAccountid(tmpctc.getAccountid());
            finalctc.setintegrationcontractid(integrationcontractid);


            System.out.println("Generated Contract from json is > " + finalctc.toString());

            String savedContractSfid = contractService.saveContract(finalctc);

            System.out.println("Saved ContractId is >" + savedContractSfid);
            map.put("ContractSfId", savedContractSfid);

        }   else {
            map.put("ContractSfId ", "-1 doesnt exist for integrationcontractid > " +integrationcontractid);
        }

        return map;
    }




    @GetMapping("/getContractSfidByIntegrationcontractid")
    @ResponseBody
    public Map<String,String> getContractSfidByIntegrationcontractid(@RequestParam(value = "integrationcontractid", defaultValue = "john@doe.com", required=false) String integrationcontractid) {

        String sfid = contractService.getContractSfId(integrationcontractid);

        Map<String, String> map = new HashMap<>();
        map.put("sfid", sfid);

        return map;
    }


    @GetMapping("/deleteContractByintegrationcontractid")
    @ResponseBody
    public Map<String,String> deleteContractByintegrationcontractid(@RequestParam(value = "integrationcontractid", defaultValue = "john@doe.com", required=true) String integrationcontractid) {

        Map<String,String> deletedContract = contractService.deleteContract(integrationcontractid);


        return deletedContract;
    }




    //updateOrInsertContract
    //lookup the Contract in salesforce
        //if email exist and all other fields are the same > return Salesforce Id to calling app

        //if email exist AXG Contract fields are different and not null > update SF and return Id

        //if AXG Contract email does not exist > insert Contract and return id







}
