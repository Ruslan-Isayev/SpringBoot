package com.project.springboot.service;

import com.project.springboot.dto.request.ReqAccount;
import com.project.springboot.dto.response.RespAccount;
import com.project.springboot.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    Response<List<RespAccount>> getAccountListByCustomerId(Long customerId);

    Response createAccount(ReqAccount reqAccount);
}
