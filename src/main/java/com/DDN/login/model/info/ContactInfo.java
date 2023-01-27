package com.DDN.login.model.info;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String office;
    private String mobile;
    private String other;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contactInfo")
    @JsonIgnore
    private List<BankInfo> banks;

    public ContactInfo() {}

    public ContactInfo(String email, String office, String mobile, String other) {
        this.email = email;
        this.office = office;
        this.mobile = mobile;
        this.other = other;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<BankInfo> getBanks() {
        return banks;
    }

    public void setBanks(List<BankInfo> banks) {
        this.banks = banks;
    }
}
