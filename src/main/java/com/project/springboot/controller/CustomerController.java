package com.project.springboot.controller;

import com.project.springboot.dto.request.ReqCustomer;
import com.project.springboot.dto.request.ReqToken;
import com.project.springboot.dto.response.RespCustomer;
import com.project.springboot.dto.response.Response;
import com.project.springboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/getCustomerList")
    public Response<List<RespCustomer>> getCustomerList(@RequestBody ReqToken reqToken) {
        return customerService.getCustomerList(reqToken);
    }

    @PostMapping("/getCustomerById")
    public Response<RespCustomer> getCustomerById(@RequestBody ReqCustomer reqCustomer) {
        return customerService.getCustomerById(reqCustomer);
    }

    @PostMapping("/addCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.addCustomer(reqCustomer);
    }

    @PutMapping("/updateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.updateCustomer(reqCustomer);
    }

    @PutMapping("/deleteCustomer")
    public Response deleteCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.deleteCustomer(reqCustomer);
    }
}