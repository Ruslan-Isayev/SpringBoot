package com.project.springboot.service.impl;

import com.project.springboot.dto.response.*;
import com.project.springboot.entity.Transaction;
import com.project.springboot.dto.request.ReqTransaction;
import com.project.springboot.entity.Account;
import com.project.springboot.enums.EnumAvailableStatus;
import com.project.springboot.exception.ExceptionConstants;
import com.project.springboot.exception.MyException;
import com.project.springboot.repository.AccountRepository;
import com.project.springboot.repository.TransactionRepository;
import com.project.springboot.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public Response<List<RespTransaction>> getTransactionList(Long accountId) {
        Response<List<RespTransaction>> response = new Response<>();
        try {
            if (accountId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Account account = accountRepository.findAccountByIdAndActive(accountId, EnumAvailableStatus.ACTIVE.value);
            if (account == null) {
                throw new MyException(ExceptionConstants.ACCOUNT_NOT_FOUND, "Account not found");
            }
            List<Transaction> transactionList = transactionRepository.findAllByDtAccountAndActive(account, EnumAvailableStatus.ACTIVE.value);
            if (transactionList.isEmpty()) {
                throw new MyException(ExceptionConstants.TRANSACTION_NOT_FOUND, "Transaction not found");
            }
            List<RespTransaction> respTransactionList = transactionList.stream().map(transaction -> mapping(transaction)).collect(Collectors.toList());
            response.setT(respTransactionList);
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

    @Override
    public Response createTransaction(ReqTransaction reqTransaction) {
        Response response = new Response();
        try {
            Long dtAccountId = reqTransaction.getDtAccountId();
            String crAccount = reqTransaction.getCrAccount();
            Double amount = reqTransaction.getAmount();
            Double commission = reqTransaction.getCommission();
            String currency = reqTransaction.getCurrency();
            if (dtAccountId == null || crAccount == null || amount == null || commission == null || currency == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Account dtAccount = accountRepository.findAccountByIdAndActive(dtAccountId, EnumAvailableStatus.ACTIVE.value);
            if (dtAccount == null) {
                throw new MyException(ExceptionConstants.DEBIT_ACCOUNT_NOT_FOUND, "Debit account not found");
            }
            if (amount <= 0) {
                throw new MyException(ExceptionConstants.INVALID_AMOUNT, "Invalid amount");
            }
            Transaction transaction = Transaction.builder()
                    .dtAccount(dtAccount)
                    .crAccount(crAccount)
                    .amount(amount)
                    .commission(commission)
                    .currency(currency)
                    .build();
            transactionRepository.save(transaction);
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

    private RespTransaction mapping(Transaction transaction) {

        RespCustomer respCustomer = RespCustomer.builder()
                .customerId(transaction.getDtAccount().getCustomer().getId())
                .name(transaction.getDtAccount().getCustomer().getName())
                .surname(transaction.getDtAccount().getCustomer().getSurname())
                .cif(transaction.getDtAccount().getCustomer().getCif())
                .build();

        RespAccount dtAccount = RespAccount.builder()
                .id(transaction.getDtAccount().getId())
                .name(transaction.getDtAccount().getName())
                .accountNo(transaction.getDtAccount().getAccountNo())
                .iban(transaction.getDtAccount().getIban())
                .currency(transaction.getDtAccount().getCurrency())
                .branchCode(transaction.getDtAccount().getBranchCode())
                .respCustomer(respCustomer)
                .build();

        RespTransaction respTransaction = RespTransaction.builder()
                .dtAccount(dtAccount)
                .crAccount(transaction.getCrAccount())
                .amount(transaction.getAmount())
                .commission(transaction.getCommission())
                .trDate(transaction.getTrDate())
                .currency(transaction.getCurrency())
                .build();
        return respTransaction;
    }

}
