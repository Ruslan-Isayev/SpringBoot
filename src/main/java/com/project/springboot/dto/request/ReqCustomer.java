package com.project.springboot.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ReqCustomer {
    private Long customerId;
    private String name;
    private String surname;
    private Date dob;
    private String phone;
}