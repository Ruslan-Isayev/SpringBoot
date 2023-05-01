package com.project.springboot.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespTransaction {
    private Long id;
    private RespAccount dtAccount;
    private String crAccount;
    private Double amount;
    private Double commission;
    private String currency;
    private Date trDate;
}
