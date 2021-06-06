package ru.voitekhov.testBankTask.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "deposits")
public class Deposit extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "start_deposit_date", nullable = false, columnDefinition = "date default now()")
    private final LocalDate startDepositDate = LocalDate.now();

    @Column(name = "interest_rate", nullable = false)
    @NotNull
    @Min(0)
    @Max(100)
    private double interestRate;

    @Column(name = "period_of_deposit")
    @NotNull
    @Min(1)
    private int periodOfDepositInMonths;

    public Deposit(Integer id, Bank bank, Client client, double interestRate, int periodOfDepositInMonths) {
        super(id);
        this.bank = bank;
        this.client = client;
        this.interestRate = interestRate;
        this.periodOfDepositInMonths = periodOfDepositInMonths;
    }

    public Deposit(double interestRate, int periodOfDepositInMonths) {
        this.interestRate = interestRate;
        this.periodOfDepositInMonths = periodOfDepositInMonths;
    }

    protected Deposit() {

    }

    public Bank getBank() {
        return bank;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getStartDepositDate() {
        return startDepositDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getPeriodOfDepositInMonths() {
        return periodOfDepositInMonths;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setPeriodOfDepositInMonths(int periodOfDeposit) {
        this.periodOfDepositInMonths = periodOfDeposit;
    }
}

