package com.project.springboot.service.impl;

import com.project.springboot.dto.request.ReqAccount;
import com.project.springboot.dto.response.RespAccount;
import com.project.springboot.dto.response.RespStatus;
import com.project.springboot.dto.response.Response;
import com.project.springboot.entity.Account;
import com.project.springboot.entity.Customer;
import com.project.springboot.enums.EnumAvailableStatus;
import com.project.springboot.exception.ExceptionConstants;
import com.project.springboot.exception.MyException;
import com.project.springboot.repository.AccountRepository;
import com.project.springboot.repository.CustomerRepository;
import com.project.springboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Response<List<RespAccount>> getAccountListByCustomerId(Long customerId) {
        Response<List<RespAccount>> response = new Response<>();
        try {
            if (customerId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            List<Account> accountList = accountRepository.findAllByCustomerAndActive(customer, EnumAvailableStatus.ACTIVE.value);
            if (accountList.isEmpty()) {
                throw new MyException(ExceptionConstants.ACCOUNT_NOT_FOUND, "Account not found");
            }
            List<RespAccount> respAccountList = accountList.stream().map(account -> mapping(account)).collect(Collectors.toList());
            response.setT(respAccountList);
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
    public Response createAccount(ReqAccount reqAccount) {
        Response response = new Response();
        try {
            String accountNo = reqAccount.getAccountNo();
            String name = reqAccount.getName();
            String iban = reqAccount.getIban();
            String branchCode = reqAccount.getBranchCode();
            String currency = reqAccount.getCurrency();
            Long customerId = reqAccount.getCustomerId();
            if (accountNo == null || name == null || iban == null || customerId == null || currency == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            Account account = Account.builder().
                    accountNo(accountNo).
                    name(name).
                    iban(iban).
                    branchCode(branchCode).
                    currency(currency).
                    customer(customer).
                    build();
            accountRepository.save(account);
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
    public Response deleteAccount(Long accountId) {
        Response response = new Response();
        try {
            if (accountId == null){
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Account account = accountRepository.findAccountByIdAndActive(accountId, EnumAvailableStatus.ACTIVE.value);
            if (account == null){
                throw new MyException(ExceptionConstants.ACCOUNT_NOT_FOUND, "Account not found");
            }
            account.setActive(EnumAvailableStatus.DEACTIVE.value);
            accountRepository.save(account);
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

    private RespAccount mapping(Account account) {
        RespAccount respAccount = RespAccount.builder().
                id(account.getId()).
                name(account.getName()).
                accountNo(account.getAccountNo()).
                iban(account.getIban()).
                currency(account.getCurrency()).
                branchCode(account.getBranchCode()).
                build();
        return respAccount;
    }
}
