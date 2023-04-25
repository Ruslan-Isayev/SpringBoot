package com.project.springboot.controller;

import com.project.springboot.dto.Request.ReqCustomer;
import com.project.springboot.dto.Response.RespCustomer;
import com.project.springboot.dto.Response.Response;
import com.project.springboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping("/getCustomerList")
    public Response<List<RespCustomer>> getCustomerList() {
        return customerService.getCustomerList();
    }

    @GetMapping("/getCustomerById/{customerId}")
    public Response<RespCustomer> getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/addCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.addCustomer(reqCustomer);
    }
}
