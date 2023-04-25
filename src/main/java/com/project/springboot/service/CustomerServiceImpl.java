package com.project.springboot.service;

import com.project.springboot.dto.Response.RespCustomer;
import com.project.springboot.dto.Response.RespStatus;
import com.project.springboot.dto.Response.Response;
import com.project.springboot.entity.Customer;
import com.project.springboot.enums.EnumAvailableStatus;
import com.project.springboot.exception.ExceptionConstants;
import com.project.springboot.exception.MyException;
import com.project.springboot.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();
        try {
            List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (customerList.isEmpty()) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            List<RespCustomer> respCustomerList = customerList.stream().map(customer -> mapping(customer)).collect(Collectors.toList());
            response.setT(respCustomerList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }

        return response;
    }

    private RespCustomer mapping(Customer customer) {
        RespCustomer respCustomer = RespCustomer.builder()
                .customerId(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .dob(customer.getDob())
                .phone(customer.getPhone())
                .build();
        return respCustomer;
    }
}
