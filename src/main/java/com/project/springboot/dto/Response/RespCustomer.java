package com.project.springboot.dto.Response;

import lombok.Data;

import java.util.Date;

@Data
public class RespCustomer {
    private Long customerId;
    private String name;
    private String surname;
    private Date dob;
    private String phone;
}
