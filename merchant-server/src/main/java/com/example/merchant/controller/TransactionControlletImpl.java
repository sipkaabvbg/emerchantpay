package com.example.merchant.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.merchant.api.TransactionController;
import com.example.merchant.repository.TransactionRepository;
import com.example.merchant.request.TransactionRequest;
import com.example.merchant.response.TransactionResponse;
import com.example.merchant.service.TransactionService;

/**
 * This class represents Transaction controller functionality, find
 * transactions, upload transaction Controllers are implemented according task
 * 7. For controllers, try to: Keep them thin Encapsulate business logic in
 * service objects
 *
 */
@RestController
@RequestMapping("/api/payment")
public class TransactionControlletImpl implements TransactionController {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	TransactionService transactionService;

	/**
	 * This method post Transaction
	 */
	@Override
   // @PreAuthorize("hasRole('MERCHANT')")
	public TransactionResponse postTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
		return transactionService.saveTransaction(transactionRequest);
	}

	/**
	 * This method find all transactions for concrete merchant
	 */
	@Override
	public List<TransactionResponse> getTransactions(String username) {
		return transactionService.getTransactions(username);
	}
}
