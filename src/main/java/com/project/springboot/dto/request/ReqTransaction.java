package com.project.springboot.dto.request;

import com.project.springboot.entity.Account;
import lombok.Data;

@Data
public class ReqTransaction {
    private Account dtAccount;
    private String crAccount;
    private Double amount;
    private Double commission;
    private String currency;
}
