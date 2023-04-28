package com.project.springboot.dto.response;

import lombok.Data;

@Data
public class RespAccount {
    private String name;
    private String accountNo;
    private String iban;
    private String currency;
    private String branchCode;
}
