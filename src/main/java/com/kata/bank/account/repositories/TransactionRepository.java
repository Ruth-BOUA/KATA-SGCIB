package com.kata.bank.account.repositories;

import com.kata.bank.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * The interface Transaction repository.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Find transaction by account id.
     *
     * @param accountId the account id
     * @return the list
     */
    @Query("Select t FROM Transaction t where t.accountId = ?1")
    List<Transaction> findTransactionByAccountId(Long accountId);

}
