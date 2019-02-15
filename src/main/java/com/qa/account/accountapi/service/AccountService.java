package com.qa.account.accountapi.service;

import org.springframework.http.ResponseEntity;

import com.qa.account.accountapi.persistence.domain.Account;
import com.qa.account.accountapi.persistence.domain.Cohort;

import java.util.List;

public interface AccountService {

    List<Account> getAccounts();
    List<Cohort> getAccountsCohorts();

    Account getAccount(Long id);
    Account addAccount(Account account);
    ResponseEntity<Object> deleteAccount(Long id);

    ResponseEntity<Object> updateAccount(Account account, Long id);

	
}
