package com.kata.bank.account.controller;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Transaction;
import com.kata.bank.account.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Bank controller.
 */
@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    /**
     * Make deposit.
     *
     * @param accountId the account id
     * @param amount    the amount
     * @return the account
     */
    @PutMapping("/{accountId}/deposit")
    @ResponseBody
    public Account makeDeposit(@PathVariable("accountId") Long accountId, @RequestParam Integer amount) {
        return bankService.makeDeposit(accountId, amount);
    }

    /**
     * Make withdrawal.
     *
     * @param accountId the account id
     * @param amount    the amount
     * @return the account
     */
    @PutMapping("/{accountId}/withdrawal")
    @ResponseBody
    public Account makeWithdrawal(@PathVariable("accountId") Long accountId, @RequestParam Integer amount) {
        return bankService.makeWithdrawal(accountId, amount);
    }

    /**
     * Gets transactions history.
     *
     * @param accountId the account id
     * @return the transactions history
     */
    @GetMapping("/{accountId}/transactions")
    @ResponseBody
    public List<Transaction> getTransactionsHistory(@PathVariable("accountId") Long accountId) {
        return bankService.getTransactionsHistory(accountId);
    }
}
