package com.qa.account.accountapi.rest;

import com.qa.account.accountapi.persistence.domain.SentAccount;
import com.qa.account.accountapi.persistence.domain.SentPrize;
import com.qa.account.accountapi.service.AccountService;

import com.qa.account.accountapi.persistence.domain.Account;
import com.qa.account.accountapi.persistence.domain.Cohort;
import com.qa.account.accountapi.persistence.domain.Prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin
@RequestMapping("${path.base}")
@RestController
public class AccountRest {

    @Autowired
    private AccountService service;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Value("${url.generator}")
    private String generatorURL;
    
    @Value("${path.genAccountNum}")
    private String accountNumGeneratorPath;
    
    @Value("${url.prize}")
    private String prizeURL;
    
    @Value("${path.determinePrize}")
    private String determinePrizePath;

    @GetMapping("${path.getAccounts}")
    public List<Account> getAccounts() {
        return service.getAccounts();
    }

    @GetMapping("${path.getAccountById}")
    public Account getAccount(@PathVariable Long id) {
        return service.getAccount(id);
    }

    @DeleteMapping("${path.deleteAccount}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        return service.deleteAccount(id);
    }

    @PutMapping("${path.updateAccount}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable Long id) {
        return service.updateAccount(account, id);
    }
    
    @PostMapping("${path.createAccount}")
    public Account createAccount(@RequestBody Account account) {
        account = setAccountNumberAndPrize(account); 
        sendToQueue(account);
    	return service.addAccount(account);
    }
    
    @PostMapping("${path.createCohort}")
    public Cohort createCohort(@RequestBody Account account,Cohort cohort) {
        account.setCohort(cohort);
    	return cohort;
    }
    
    @GetMapping("${path.getCohortById}")
    public Cohort getCohort(@PathVariable Long id) {
        return service.getAccount(id).getCohort();
    }
    @GetMapping("${path.getCohorts}")
    public List<Cohort> getCohorts() {
        return service.getAccountsCohorts();
    }

    private Account setAccountNumberAndPrize(Account account){
        String generatedAccountNum = restTemplate.getForObject(generatorURL + accountNumGeneratorPath, String.class);
        Prize prizeWon = restTemplate.getForObject(prizeURL + determinePrizePath + generatedAccountNum, Prize.class);

        account.setAccountNumber(generatedAccountNum);
        account.setPrize(prizeWon);
        return account;
    }

    private void sendToQueue(Account account){
        SentAccount accountToStore =  new SentAccount(account);
        jmsTemplate.convertAndSend("AccountQueue", accountToStore);
    }

}
