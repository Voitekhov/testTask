package ru.voitekhov.testBankTask.model;

public enum LegalStatus {
    LIMITED_LIABILITY_COMPANY("Общества с ограниченной ответственностью"),
    PUBLIC_JOINT_STOCK_COMPANY("Публичное акционерное общество");
    private final String title;

    LegalStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
