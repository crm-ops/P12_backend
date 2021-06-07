package com.p12.postgresbackend.model;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    @Column(name="integration_email__c")
    private String integrationemail;
    private String sfid;
    private String firstname;
    private String lastname;
    private String email;
    private String accountid;
    private String mailingcity;
    private Boolean isdeleted = false;







    public Contact() {
    }




    public Contact(Long id,String integrationemail, String sfid, String firstname, String lastname, String email, String accountid, String mailingcity,  Boolean isdeleted) {

        this.id = id;
        this.integrationemail=integrationemail;
        this.sfid=sfid;
        this.firstname=firstname;
        this.lastname= lastname;
        this.email=email;
        this.accountid=accountid;
        this.mailingcity=mailingcity;

        this.isdeleted=isdeleted;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSfId() {
        return sfid;
    }

    public void setSfid(String sfid) {
        this.sfid = sfid;
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

    public String getIntegrationemail() {
        return integrationemail;
    }

    public void setIntegrationemail(String integrationemail) {
        this.integrationemail= integrationemail;
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
        hash = 79 * hash + Objects.hashCode(this.integrationemail);
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

        if (!Objects.equals(this.integrationemail, other.integrationemail)) {
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
        sb.append(", integrationemail='").append(integrationemail).append('\'');
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
        jo.put("integrationemail",integrationemail);
        jo.put("isdeleted",isdeleted);
        return jo;
    }

}
