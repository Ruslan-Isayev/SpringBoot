package com.project.springboot.dto.Request;

import lombok.Data;

import java.util.Date;

@Data
public class ReqCustomer {
    private String name;
    private String surname;
    private Date dob;
    private String phone;
}