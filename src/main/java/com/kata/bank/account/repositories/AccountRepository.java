package com.kata.bank.account.repositories;

import com.kata.bank.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Account repository.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}
