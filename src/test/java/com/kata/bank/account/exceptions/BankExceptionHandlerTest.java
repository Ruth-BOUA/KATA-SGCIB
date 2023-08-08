package com.kata.bank.account.exceptions;

import com.kata.bank.account.model.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankExceptionHandlerTest {

    private BankExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new BankExceptionHandler();
    }

    @Test
    public void handleInvalidAmountException() {
        InvalidAmountException exception = new InvalidAmountException("Invalid amount");
        ResponseEntity<ErrorMessage> response = exceptionHandler.handleInvalidAmountException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid amount", response.getBody().getMessage());
    }

    @Test
    public void handleAccountNotFoundException() {
        AccountNotFoundException exception = new AccountNotFoundException("Account not found");
        ResponseEntity<ErrorMessage> response = exceptionHandler.handleAccountNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Account not found", response.getBody().getMessage());
    }

}






