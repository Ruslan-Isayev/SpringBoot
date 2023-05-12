package com.project.springboot.service;

import com.project.springboot.dto.request.ReqCustomer;
import com.project.springboot.dto.request.ReqToken;
import com.project.springboot.dto.response.RespCustomer;
import com.project.springboot.dto.response.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface CustomerService {

    Response<List<RespCustomer>> getCustomerList(ReqToken reqToken);

    Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(ReqCustomer reqCustomer);
}
