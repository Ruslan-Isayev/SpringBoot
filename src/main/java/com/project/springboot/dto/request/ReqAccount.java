package com.project.springboot.dto.request;

import lombok.Data;

@Data
public class ReqAccount {
    private String name;
    private String accountNo;
    private String iban;
    private String currency;
    private String branchCode;
    private Long customerId;
}
