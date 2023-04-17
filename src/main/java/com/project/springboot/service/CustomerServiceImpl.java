package com.project.springboot.service;

import com.project.springboot.dto.Response.RespCustomer;
import com.project.springboot.entity.Customer;
import com.project.springboot.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<RespCustomer> getCustomerList() {
        List<RespCustomer> response = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAllByActive(1);
        for (Customer customer : customerList) {
            RespCustomer respCustomer = new RespCustomer();
            respCustomer.setCustomerId(customer.getId());
            respCustomer.setName(customer.getName());
            respCustomer.setSurname(customer.getSurname());
            respCustomer.setDob(customer.getDob());
            respCustomer.setPhone(customer.getPhone());
            response.add(respCustomer);
        }
        return response;
    }
}
