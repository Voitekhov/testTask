package ru.voitekhov.testBankTask.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "clients")
public class Client extends AbstractBaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "legal_status", nullable = false)
    private LegalStatus legalStatus;

    public Client(Integer id, String name, String address, LegalStatus legalStatus) {
        super(id);
        this.name = name;
        this.address = address;
        this.legalStatus = legalStatus;
    }

    public Client(String name, String address, LegalStatus legalStatus) {
        this.name = name;
        this.address = address;
        this.legalStatus = legalStatus;
    }

    protected Client() {

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LegalStatus getLegalStatus() {
        return legalStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLegalStatus(LegalStatus legalStatus) {
        this.legalStatus = legalStatus;
    }
}
