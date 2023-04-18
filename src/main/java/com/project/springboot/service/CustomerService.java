package com.project.springboot.service;

import com.project.springboot.dto.Response.RespCustomer;
import com.project.springboot.dto.Response.Response;

import java.util.List;

public interface CustomerService {

    Response<List<RespCustomer>> getCustomerList();

}
