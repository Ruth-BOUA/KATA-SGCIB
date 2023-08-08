package com.kata.bank.account.service;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.model.Transaction;
import com.kata.bank.account.repositories.AccountRepository;
import com.kata.bank.account.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    public static final long ACCOUNT_ID = 1L;
    public static final int INITIAL_BALANCE = 100;
    public static final long TRANSACTION_ID = 1L;
    BankService bankService;
    @Mock
    AccountRepository accountRepositoryMock;
    @Mock
    TransactionRepository transactionRepositoryMock;
    Account account;
    Transaction transaction;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
        bankService = new BankService(accountRepositoryMock, transactionRepositoryMock);
        account = new Account(ACCOUNT_ID, INITIAL_BALANCE);
        transaction = new Transaction(TRANSACTION_ID, ACCOUNT_ID, Operation.DEPOSIT, INITIAL_BALANCE, new Date(System.currentTimeMillis()));

        doReturn(Optional.of(account)).when(accountRepositoryMock).findById(ACCOUNT_ID);

    }

    @Test
    void makeDeposit() {

        Integer newBalance = bankService.makeDeposit(ACCOUNT_ID, 10).getBalance();

        assertEquals(INITIAL_BALANCE + 10, newBalance);

    }

    @Test
    void makeWithdrawal() {

        Integer newBalance = bankService.makeWithdrawal(TRANSACTION_ID, 10).getBalance();

        assertEquals(INITIAL_BALANCE - 10, newBalance);

    }

    @Test
    void getTransactionsHistory() {
        doReturn(List.of(transaction)).when(transactionRepositoryMock).findTransactionByAccountId(ACCOUNT_ID);

        List<Transaction> transactionList = bankService.getTransactionsHistory(ACCOUNT_ID);

        assertEquals(List.of(transaction), transactionList);
    }
}