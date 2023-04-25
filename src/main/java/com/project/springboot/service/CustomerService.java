package com.project.springboot.service;

import com.project.springboot.dto.Request.ReqCustomer;
import com.project.springboot.dto.Response.RespCustomer;
import com.project.springboot.dto.Response.Response;

import java.util.List;

public interface CustomerService {

    Response<List<RespCustomer>> getCustomerList();

    Response<RespCustomer> getCustomerById(Long customerId);

    Response addCustomer(ReqCustomer reqCustomer);
}
