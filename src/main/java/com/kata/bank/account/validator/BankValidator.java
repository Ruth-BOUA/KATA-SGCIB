package com.kata.bank.account.validator;

import com.kata.bank.account.exceptions.AccountNotFoundException;
import com.kata.bank.account.exceptions.InvalidAmountException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.repositories.AccountRepository;

import java.util.NoSuchElementException;

import static java.text.MessageFormat.format;

/**
 * The type Bank validator.
 */
public class BankValidator {

    /**
     * Validate amount.
     *
     * @param operation the operation
     * @param account   the account
     * @param amount    the amount
     */
    public static void validateAmount(Operation operation, Account account, Integer amount) {
        if (amount < 0) {
            throw new InvalidAmountException("The amount must be a positive number.");
        }
        if (operation.equals(Operation.WITHDRAWAL) && account.getBalance() < amount) {
            throw new InvalidAmountException("Insufficient balance. The maximum withdrawal amount is " + account.getBalance());
        }
    }

    /**
     * Find account.
     *
     * @param accountId the account id
     * @return the account
     */
    public static Account findAccount(AccountRepository accountRepository, Long accountId) {
        try {
            return accountRepository.findById(accountId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new AccountNotFoundException(format("Account with id {0} does not exist.", accountId));
        }
    }
}
