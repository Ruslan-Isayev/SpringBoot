package com.project.springboot.repository;

import com.project.springboot.entity.Account;
import com.project.springboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByCustomerAndActive(Customer customer, Integer active);

    Account findAccountByIdAndActive(Long accountId, Integer active);
}
