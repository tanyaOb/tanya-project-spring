package com.project.aynat.dto;

import javax.validation.constraints.Min;

public class AccountDTO {
    private String userName;
    @Min(value = 0, message = "Amount should be a positive number")
    private Integer account;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }
}
