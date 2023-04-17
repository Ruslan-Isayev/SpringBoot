package com.project.springboot.service;

import com.project.springboot.dto.Response.RespCustomer;

import java.util.List;

public interface CustomerService {

    List<RespCustomer> getCustomerList();

}
