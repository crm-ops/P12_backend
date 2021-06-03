package com.p12.postgresbackend.model;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String integration_email__c;
    private String sfid;
    private String firstname;
    private String lastname;
    private String email;
    private String accountid;
    private String mailingcity;
    private Boolean isdeleted = false;







    public Contact() {
    }




    public Contact(Long id, String sfid, String firstname, String lastname, String email, String accountid, String mailingcity, String integration_email__c, Boolean isdeleted) {

        this.id = id;
        this.sfid=sfid;
        this.firstname=firstname;
        this.lastname= lastname;
        this.email=email;
        this.accountid=accountid;
        this.mailingcity=mailingcity;
        this.integration_email__c=integration_email__c;
        this.isdeleted=isdeleted;


    }

    public Long getId() {
        return id;
    }

    public String getSfId() {
        return sfid;
    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastame(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getMailingcity() {
        return mailingcity;
    }

    public void setMailingcity(String mailingcity) {
        this.mailingcity = mailingcity;
    }

    public String getIntegration_email__c() {
        return integration_email__c;
    }

    public void setIntegration_email__c(String integration_email__c) {
        this.integration_email__c = integration_email__c;
    }


    public Boolean getIsDeleted() {
        return isdeleted;
    }

    public void setIsDeleted( Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.sfid);
        hash = 79 * hash + Objects.hashCode(this.firstname);
        hash = 79 * hash + Objects.hashCode(this.lastname);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.accountid);
        hash = 79 * hash + Objects.hashCode(this.mailingcity);
        hash = 79 * hash + Objects.hashCode(this.integration_email__c);
        hash = 79 * hash + Objects.hashCode(this.isdeleted);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;

        if (!Objects.equals(this.sfid, other.sfid)) {
            return false;
        }



        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }

        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }

        if (!Objects.equals(this.email, other.email)) {
            return false;
        }

        if (!Objects.equals(this.accountid, other.accountid)) {
            return false;
        }

        if (!Objects.equals(this.mailingcity, other.mailingcity)) {
            return false;
        }

        if (!Objects.equals(this.integration_email__c, other.integration_email__c)) {
            return false;
        }

        if (!Objects.equals(this.isdeleted, other.isdeleted)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contact{");
        sb.append("id=").append(id);
        sb.append("sfid=").append(sfid);
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", accountid='").append(accountid).append('\'');
        sb.append(", mailingcity='").append(mailingcity).append('\'');
        sb.append(", integration_email__c='").append(integration_email__c).append('\'');
        sb.append(", isdeleted=").append(isdeleted);
        sb.append('}');
        return sb.toString();
    }


    public JSONObject toJson() throws JSONException {
        final JSONObject jo = new JSONObject();
        jo.put("id",id);
        jo.put("sfid", sfid);
        jo.put("fistname",firstname);
        jo.put("lastname",lastname);
        jo.put("email",email);
        jo.put("accountid",accountid);
        jo.put("mailingcity",mailingcity);
        jo.put("integration_email__c",integration_email__c);

        jo.put("isdeleted",isdeleted);
        return jo;
    }

}
