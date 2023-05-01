package com.project.springboot.controller;

import com.project.springboot.dto.request.ReqTransaction;
import com.project.springboot.dto.response.RespTransaction;
import com.project.springboot.dto.response.Response;
import com.project.springboot.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/getTransactionList/{accountId}")
    public Response<List<RespTransaction>> getTransactionList(@PathVariable Long accountId) {
        return transactionService.getTransactionList(accountId);
    }

    @PostMapping("/createTransaction")
    public Response createTransaction(ReqTransaction reqTransaction) {
        return transactionService.createTransaction(reqTransaction);
    }
}
