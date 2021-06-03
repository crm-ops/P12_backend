package com.p12.postgresbackend.repository;


import com.p12.postgresbackend.model.Contact;
import com.p12.postgresbackend.model.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {




    Contract findByIntegrationcontractid(String integrationcontractid);

}







