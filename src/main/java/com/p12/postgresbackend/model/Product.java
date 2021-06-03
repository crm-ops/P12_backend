package com.p12.postgresbackend.model;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product2")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String sfid;
    private String name;
    private Date createddate;
    private Timestamp systemmodstamp;
    private String description;
    private String displayurl;
    private String family;
    private Boolean isdeleted = false;









    public Product() {
    }


    public Product(String sfid, String name, Date createddate, Timestamp systemmodstamp, String description, String displayurl, String family, Boolean isdeleted) {
        this.sfid=sfid;
        this.name = name;
        this.createddate = createddate;
        this.systemmodstamp = systemmodstamp;
        this.description = description;
        this.displayurl = displayurl;
        this.family = family;
        this.isdeleted = isdeleted;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSfid() {
        return sfid;
    }

    public void setSfid(String sfid) {
        this.sfid = sfid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Timestamp getSystemmodstamp() {
        return systemmodstamp;
    }

    public void setSystemmodstamp(Timestamp systemmodstamp) {
        this.systemmodstamp = systemmodstamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayurl() {
        return displayurl;
    }

    public void setDisplayurl(String displayurl) {
        this.displayurl = displayurl;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
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
        Product product = (Product) o;
        return getId().equals(product.getId()) &&
                Objects.equals(getSfid(),product.getSfid()) &&
                Objects.equals(getName(), product.getName()) &&
                getCreateddate().equals(product.getCreateddate()) &&
                getSystemmodstamp().equals(product.getSystemmodstamp()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                Objects.equals(getDisplayurl(), product.getDisplayurl()) &&
                Objects.equals(getFamily(), product.getFamily()) &&
                getIsdeleted().equals(product.getIsdeleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSfid(),getName(), getCreateddate(), getSystemmodstamp(), getDescription(), getDisplayurl(), getFamily(), getIsdeleted());
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                "sfid=" + sfid +
                ", name='" + name + '\'' +
                ", createddate=" + createddate +
                ", systemmodstamp=" + systemmodstamp +
                ", description='" + description + '\'' +
                ", displayurl='" + displayurl + '\'' +
                ", family='" + family + '\'' +
                ", isdeleted=" + isdeleted +
                '}';
    }

    public JSONObject toJson() throws JSONException {
        final JSONObject jo = new JSONObject();
        jo.put("id",id);
        jo.put("sfid",sfid);
        jo.put("name",name);
        jo.put("createddate",createddate);
        jo.put("systemmodstamp",systemmodstamp);
        jo.put("description",description);
        jo.put("displayurl",displayurl);
        jo.put("family",family);
        jo.put("isdeleted",isdeleted);
        return jo;
    }

}
