package com.kata.bank.account.validator;

import com.kata.bank.account.exceptions.AccountNotFoundException;
import com.kata.bank.account.exceptions.InvalidAmountException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BankValidatorTest {
    public static final long ACCOUNT_ID = 1L;
    Account account;
    @Mock
    AccountRepository accountRepositoryMock;

    @BeforeEach
    void setUp() {
        account = new Account(ACCOUNT_ID, 10);
    }

    @Test
    void validateAmountForWithdrawal() {

        assertDoesNotThrow(() -> BankValidator.validateAmount(Operation.WITHDRAWAL, account, 5));
    }

    @Test
    void validateAmountForDeposit() {

        assertDoesNotThrow(() -> BankValidator.validateAmount(Operation.DEPOSIT, account, 10));
    }

    @Test
    void validateAmountForNegativeAmount() {

        assertThrows(InvalidAmountException.class, () -> BankValidator.validateAmount(Operation.DEPOSIT, account, -10));
    }


    @Test
    void validateAmountForWithdrawalWithInsufficientBalance() {

        assertThrows(InvalidAmountException.class, () -> BankValidator.validateAmount(Operation.WITHDRAWAL, account, 20));
    }

    @Test
    void findAccount() {
        doReturn(Optional.of(account)).when(accountRepositoryMock).findById(ACCOUNT_ID);

        assertEquals(account, BankValidator.findAccount(accountRepositoryMock, ACCOUNT_ID));
    }

    @Test
    void findAccountWithAccountNotFound() {
        assertThrows(AccountNotFoundException.class, () -> BankValidator.findAccount(accountRepositoryMock, ACCOUNT_ID));
    }
}