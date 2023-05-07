package com.project.springboot.service;

import com.project.springboot.dto.request.ReqCustomer;
import com.project.springboot.dto.response.RespCustomer;
import com.project.springboot.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Response<List<RespCustomer>> getCustomerList();

    Response<RespCustomer> getCustomerById(Long customerId);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(Long customerId);
}
