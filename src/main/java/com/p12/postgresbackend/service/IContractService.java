package com.p12.postgresbackend.service;



import com.p12.postgresbackend.model.Contact;
import com.p12.postgresbackend.model.Contract;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IContractService {

    List<Contract> findAll();

    String saveContract(Contract contract) throws InterruptedException;

    Contract getContractBySfid(String sfid);

    String getContractSfId(String integrationcontractid__c);

    Map<String,String> deleteContract(String integrationcontractid__c);

    Optional<Contract> findById(Long id);

    Contract getContractByIntegrationcontractid(String integrationcontractid);

}
