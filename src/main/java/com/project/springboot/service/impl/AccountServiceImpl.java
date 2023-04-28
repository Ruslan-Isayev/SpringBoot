package com.project.springboot.service.impl;

import com.project.springboot.dto.request.ReqAccount;
import com.project.springboot.dto.response.RespAccount;
import com.project.springboot.dto.response.RespStatus;
import com.project.springboot.dto.response.Response;
import com.project.springboot.exception.ExceptionConstants;
import com.project.springboot.exception.MyException;
import com.project.springboot.repository.AccountRepository;
import com.project.springboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Response<List<RespAccount>> getAccountListByCustomerId(Long customerId) {
        Response<List<RespAccount>> response = new Response<>();
        try {
            if (customerId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Response createAccount(ReqAccount reqAccount) {
        return null;
    }
}
