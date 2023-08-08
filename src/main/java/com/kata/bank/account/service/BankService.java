package com.kata.bank.account.service;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.model.Transaction;
import com.kata.bank.account.repositories.AccountRepository;
import com.kata.bank.account.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.kata.bank.account.validator.BankValidator.findAccount;
import static com.kata.bank.account.validator.BankValidator.validateAmount;


/**
 * The type Bank service.
 */
@Service
@AllArgsConstructor
public class BankService {
    /**
     * The lock.
     */
    public static Lock lock = new ReentrantLock();
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Make deposit on account.
     *
     * @param accountId the account id
     * @param amount    the amount
     * @return the account with the new balance
     */
    @Transactional
    public Account makeDeposit(Long accountId, Integer amount) {
        // create a lock to ensure that no other thread accesses the method during the transaction
        lock.lock();

        // Check that account id exists
        Account account = findAccount(accountRepository, accountId);

        // check that amount is positive
        validateAmount(Operation.DEPOSIT, account, amount);

        // save transaction
        Transaction deposit = createTransaction(accountId, amount, Operation.DEPOSIT);

        transactionRepository.save(deposit);

        // update account balance
        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);

        // remove the lock
        lock.unlock();

        return account;
    }


    /**
     * Make withdrawal from account.
     *
     * @param accountId the account id
     * @param amount    the amount
     * @return the account with the new balance
     */
    @Transactional
    public Account makeWithdrawal(Long accountId, Integer amount) {
        // create a lock to ensure that no other thread accesses the method during the transaction
        lock.lock();

        // Check that account id exists
        Account account = findAccount(accountRepository, accountId);

        // check that amount is positive and less than account balance
        validateAmount(Operation.WITHDRAWAL, account, amount);

        // save transaction
        Transaction withdrawal = createTransaction(accountId, amount, Operation.WITHDRAWAL);

        transactionRepository.save(withdrawal);

        // update account balance
        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);

        // remove the lock
        lock.unlock();

        return account;
    }

    private Transaction createTransaction(Long accountId, Integer amount, Operation operation) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setOperation(operation);
        transaction.setAmount(amount);
        transaction.setExecutionDate(new Date(System.currentTimeMillis()));
        return transaction;
    }


    /**
     * Gets transactions history.
     *
     * @param accountId the account id
     * @return the transactions history
     */
    public List<Transaction> getTransactionsHistory(Long accountId) {
        // Check that account id exists
        findAccount(accountRepository, accountId);

        return transactionRepository.findTransactionByAccountId(accountId);
    }
}
