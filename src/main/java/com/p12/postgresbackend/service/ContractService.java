package com.p12.postgresbackend.service;


import com.p12.postgresbackend.model.Contact;
import com.p12.postgresbackend.model.Contract;
import com.p12.postgresbackend.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContractService implements IContractService {

    @Autowired
    private ContractRepository repository;

    @Override
    public List<Contract> findAll() {

        var Contracts = (List<Contract>) repository.findAll();

        return Contracts;
    }

    @Override
    public Optional<Contract> findById(Long id) {

        return repository.findById(id);
    }


    @Override
    public Contract getContractBySfid(String sfid) {

        return repository.findBySfid(sfid);
    }

    @Override
    public Contract getContractByIntegrationcontractid(String integrationcontractid) {

        return repository.findByIntegrationcontractid(integrationcontractid);
    }



    @Override
    public String saveContract(Contract Contract) throws InterruptedException {


        Contract foundContract;
        String returnedSfid;

        if (repository.findByIntegrationcontractid(Contract.getIntegrationcontractid()) == null) {

            System.out.println("No Existing Contract in DB with reference > " + Contract.getIntegrationcontractid() + " ... inserting new record");
            Contract savedContract = repository.save(Contract);


            savedContract = repository.findByIntegrationcontractid(Contract.getIntegrationcontractid());

            System.out.println("Retrieved saved Contract from DB with integrationcontractid > " + savedContract.getIntegrationcontractid() + " \n");
            System.out.println("Retrieved saved Contract from DB with sfid > " + savedContract.getSfid());

            returnedSfid = savedContract.getSfid();


        } else {

            foundContract = repository.findByIntegrationcontractid(Contract.getIntegrationcontractid());
            System.out.println("Existing Contract in DB with reference > " + Contract.getIntegrationcontractid() + " and with sfid " + foundContract.getSfid());
            foundContract.setAccountid(Contract.getAccountid());
            foundContract.setContractterm(Contract.getContractterm());
            foundContract.setDescription(Contract.getDescription());
            foundContract.setSpecialterms(Contract.getSpecialterms());
            foundContract.setStartdate(Contract.getStartdate());
            foundContract.setStatus(Contract.getStatus());



            returnedSfid = foundContract.getSfid();

            repository.save(foundContract);


        }

        return returnedSfid;
    }


    @Override
    public String getContractSfId(String Integrationcontractid) {

        String sfid;

        if (repository.findByIntegrationcontractid(Integrationcontractid) == null) {

            sfid = "notfound";

        } else {

            sfid = repository.findByIntegrationcontractid(Integrationcontractid).getSfid();


        }

        return sfid;


    }








    @Override
    public Map<String,String> deleteContract(String integrationcontractid) {

        Map<String,String> deletedContract = new HashMap<>();

        if (repository.findByIntegrationcontractid(integrationcontractid) == null) {

            deletedContract.put(integrationcontractid, "not found");

        } else {
            Contract Contracttodel = repository.findByIntegrationcontractid(integrationcontractid);

            repository.delete(Contracttodel);

            deletedContract.put(integrationcontractid,"deleted in heroku db");

        }

        return deletedContract;


    }
}