package com.p12.postgresbackend.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String firstname;
    private String lastname;
    private Boolean softdeleted__c;







    public Contact() {
    }

    public Contact(String id, String name, String firstname, String lastname, Boolean softdeleted__c) {

        this.id = id;
        this.name = name;
        this.firstname=firstname;
        this.lastname= lastname;
        this.softdeleted__c=softdeleted__c;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getSoftdeleted__c() {
        return softdeleted__c;
    }

    public void setSoftdeleted__c( Boolean softdeleted__c) {
        this.softdeleted__c = softdeleted__c;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.firstname);
        hash = 79 * hash + Objects.hashCode(this.lastname);
        hash = 79 * hash + Objects.hashCode(this.softdeleted__c);

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

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }

        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.softdeleted__c, other.softdeleted__c)) {
            return false;
        }


        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", softdeleted__c=").append(softdeleted__c);
        sb.append('}');
        return sb.toString();
    }
}
