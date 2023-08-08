package com.kata.bank.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Transaction;
import com.kata.bank.account.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BankController.class)
class BankControllerTest {
    private static final long ACCOUNT_ID = 1L;
    private static final int AMOUNT = 100;
    private static final String BASE_URL = "/api/v1/bank/{accountId}/";
    private static final String AMOUNT_PARAM = "amount";
    Account account;
    @MockBean
    private BankService bankService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        account = new Account(ACCOUNT_ID, AMOUNT);
    }

    @Test
    void makeDeposit() throws Exception {

        when(bankService.makeDeposit(ACCOUNT_ID, AMOUNT)).thenReturn(account);

        mockMvc.perform(put(BASE_URL + "deposit", ACCOUNT_ID)
                        .param(AMOUNT_PARAM, String.valueOf(AMOUNT)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(account)));
    }

    @Test
    void makeWithdrawal() throws Exception {
        when(bankService.makeWithdrawal(ACCOUNT_ID, AMOUNT)).thenReturn(account);

        mockMvc.perform(put(BASE_URL + "withdrawal", ACCOUNT_ID)
                        .param(AMOUNT_PARAM, String.valueOf(AMOUNT)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(account)));
    }

    @Test
    void getTransactionsHistory() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        when(bankService.getTransactionsHistory(ACCOUNT_ID)).thenReturn(transactions);

        mockMvc.perform(get(BASE_URL + "transactions", ACCOUNT_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(transactions)));
    }
}