package com.project.springboot.dto.request;

import lombok.Data;

@Data
public class ReqTransaction {
    private Long dtAccountId;
    private String crAccount;
    private Double amount;
    private Double commission;
    private String currency;
}