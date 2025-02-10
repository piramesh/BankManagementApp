package com.program.bankapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.program.bankapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
