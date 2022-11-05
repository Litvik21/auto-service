package com.example.autoservice.model;

public enum StatusPaid {
    PAID("Paid"),
    NOT_PAID("NotPaid");
    private String value;

    StatusPaid(String value) {
        this.value = value;
    }
}
