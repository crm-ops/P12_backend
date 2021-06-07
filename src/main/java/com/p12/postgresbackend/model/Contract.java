package com.p12.postgresbackend.model;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    @Column(name="integrationcontractid__c")
    private String integrationcontractid;
    private String sfid;
    private String accountid;
    private Date startdate;
    private String status;
    private Long   contractterm;
    private String specialterms;
    private String description;
    private Boolean isdeleted = false;







    public Contract() {
    }



    public Contract(Long id, String integrationcontractid, String sfid, String accountid, Date startdate, String status, Long contractterm, String specialterms, String description, Boolean isdeleted) {
        this.id = id;
        this.integrationcontractid = integrationcontractid;
        this.sfid = sfid;
        this.accountid = accountid;
        this.startdate = startdate;
        this.status = status;
        this.contractterm = contractterm;
        this.specialterms = specialterms;
        this.description = description;
        this.isdeleted = isdeleted;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getIntegrationcontractid() {
        return integrationcontractid;
    }

    public void setintegrationcontractid(String integrationcontractid) {
        this.integrationcontractid = integrationcontractid;
    }

    public String getSfid() {
        return sfid;
    }

    public void setSfid(String sfid) {
        this.sfid = sfid;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getContractterm() {
        return contractterm;
    }

    public void setContractterm(Long contractterm) {
        this.contractterm = contractterm;
    }

    public String getSpecialterms() {
        return specialterms;
    }

    public void setSpecialterms(String specialterms) {
        this.specialterms = specialterms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id.equals(contract.id) &&
                getIntegrationcontractid().equals(contract.getIntegrationcontractid()) &&
                getSfid().equals(contract.getSfid()) &&
                getAccountid().equals(contract.getAccountid()) &&
                getStartdate().equals(contract.getStartdate()) &&
                getStatus().equals(contract.getStatus()) &&
                getContractterm().equals(contract.getContractterm()) &&
                Objects.equals(getSpecialterms(), contract.getSpecialterms()) &&
                Objects.equals(getDescription(), contract.getDescription()) &&
                getIsdeleted().equals(contract.getIsdeleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getIntegrationcontractid(), getSfid(), getAccountid(), getStartdate(), getStatus(), getContractterm(), getSpecialterms(), getDescription(), getIsdeleted());
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", integrationcontractid='" + integrationcontractid + '\'' +
                ", sfid='" + sfid + '\'' +
                ", accountid='" + accountid + '\'' +
                ", startdate='" + startdate + '\'' +
                ", status='" + status + '\'' +
                ", contractterm=" + contractterm +
                ", specialterms='" + specialterms + '\'' +
                ", description='" + description + '\'' +
                ", isdeleted=" + isdeleted +
                '}';
    }





    public JSONObject toJson() throws JSONException {
        final JSONObject jo  = new JSONObject();
                jo.put("id",id );
                jo.put("integrationcontractid",  integrationcontractid   );
                jo.put("sfid" , sfid   );
                jo.put( "accountid",  accountid   );
                jo.put( "startdate",  startdate   );
                jo.put( "status" , status   );
                jo.put( "contractterm",  contractterm );
                jo.put( "specialterms" , specialterms   );
                jo.put( "description",  description   );
                jo.put( "isdeleted" , isdeleted );

        return jo;
    }


}
