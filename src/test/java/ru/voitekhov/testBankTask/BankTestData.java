package ru.voitekhov.testBankTask;

import ru.voitekhov.testBankTask.model.Bank;

import java.util.Arrays;
import java.util.List;

public class BankTestData {
    public static Bank BANK_IN_DB = new Bank(3, "Alpha", "33333333");

    public static List<Bank> BANKS_IN_DB = Arrays.asList(new Bank(1, "Sber", "11111111"),
            new Bank(2, "Tinkof", "22222222"), new Bank(3, "Alpha", "33333333"));

    public static List<Bank> SORTED_BANKS_IN_DB = Arrays.asList(new Bank(3, "Alpha", "33333333"),
            new Bank(1, "Sber", "11111111"), new Bank(2, "Tinkof", "22222222"));

    public static Bank BANK_NOT_IN_DB = new Bank(-1, "NotInDB", "12345678");

}
