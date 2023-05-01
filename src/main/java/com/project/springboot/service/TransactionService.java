package com.project.springboot.service;

import com.project.springboot.dto.request.ReqTransaction;
import com.project.springboot.dto.response.RespTransaction;
import com.project.springboot.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    Response<List<RespTransaction>> getTransactionList(Long accountId);

    Response createTransaction(ReqTransaction reqTransaction);
}
