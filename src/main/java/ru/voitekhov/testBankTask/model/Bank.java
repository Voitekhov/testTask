package ru.voitekhov.testBankTask.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "banks")
public class Bank extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "bik", nullable = false,unique = true)
    @Pattern(regexp = "[0-9]{8}", message = "Dab BIK format")
    private String bik;

    public Bank(Integer id, String name, String bik) {
        super(id);
        this.name = name;
        this.bik = bik;
    }

    public Bank(String name, String bik) {
        this.name = name;
        this.bik = bik;
    }

    protected Bank() { }

    public void setName(String name) {
        this.name = name;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getName() {
        return name;
    }

    public String getBik() {
        return bik;
    }
}
