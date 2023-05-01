package com.project.springboot.controller;

import com.project.springboot.dto.request.ReqAccount;
import com.project.springboot.dto.response.RespAccount;
import com.project.springboot.dto.response.Response;
import com.project.springboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @GetMapping("/getAccountListByCustomerId/{customerId}")
    public Response<List<RespAccount>> getAccountListByCustomerId(@PathVariable Long customerId){
        return accountService.getAccountListByCustomerId(customerId);
    }

    @PostMapping("/createAccount")
    public Response createAccount(@RequestBody ReqAccount reqAccount){
        return accountService.createAccount(reqAccount);
    }

    @PutMapping("/deleteAccount/{accountId}")
    public Response deleteAccount(@PathVariable Long accountId){
        return accountService.deleteAccount(accountId);
    }
}
