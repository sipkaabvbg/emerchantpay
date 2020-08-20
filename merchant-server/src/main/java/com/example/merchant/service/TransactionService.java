package com.example.merchant.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.merchant.converter.TransactionResponseConverter;
import com.example.merchant.model.TransactionEntity;
import com.example.merchant.payment.Transaction;
import com.example.merchant.payment.TransactionFactory;
import com.example.merchant.repository.TransactionRepository;
import com.example.merchant.request.TransactionRequest;
import com.example.merchant.response.TransactionResponse;

/**
 * This class is service for User
 *
 */

@Service
public class TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	TransactionFactory transactionFactory;

	/**
	 * This method saves Transaction
	 * 
	 * @param transactionRequest
	 * @return
	 */
	public TransactionResponse saveTransaction(TransactionRequest transactionRequest) {
		Transaction transaction;
		TransactionEntity transactionEntity;

		transaction = transactionFactory.getTransaction(transactionRequest);
		transactionEntity = transaction.payment(transactionRequest);
		return TransactionResponseConverter.toWrapper(transactionRepository.save(transactionEntity));
	}

	/**
	 * This method finds added Transactions by Username
	 * 
	 * @param username
	 * @return
	 */
	public List<TransactionResponse> getTransactions(String username) {
		List<TransactionResponse> result;
		List<TransactionEntity> transactionEntityList;
		result = new ArrayList<TransactionResponse>();
		transactionEntityList = transactionRepository.findAllByUserUsername(username);
		transactionEntityList.forEach(transactionEntity -> {
			result.add(TransactionResponseConverter.toWrapper(transactionEntity));
		});
		return result;
	}
}