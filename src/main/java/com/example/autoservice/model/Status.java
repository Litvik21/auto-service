package com.example.autoservice.model;

public enum Status {
    RECEIVED("Received"),
    PROCESSING("Process"),
    SUCCESSFULLY_COMPLETED("SC"),
    NOT_SUCCESSFULLY_COMPLETED("NotSC"),
    PAID("Paid");
    private String value;

    Status(String value) {
        this.value = value;
    }
}
