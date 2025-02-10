package com.program.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.bankapp.entity.Account;
import com.program.bankapp.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repo;
	
	@Override
	public Account createAccount(Account account) {
		Account accountsaved = repo.save(account);
		return accountsaved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
	
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountfound=account.get();
		
		return accountfound;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> listOfAccounts = repo.findAll();
		return listOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent=account.get();
		Double totalBalance= accountPresent.getAccount_balance()+amount;
		accountPresent.setAccount_balance(totalBalance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent=account.get();
		
		Double accountBalance = accountPresent.getAccount_balance()-amount;
		accountPresent.setAccount_balance(accountBalance);
		repo.save(accountPresent);
		
		return accountPresent;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		getAccountDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);
		
		
		
	}

}
