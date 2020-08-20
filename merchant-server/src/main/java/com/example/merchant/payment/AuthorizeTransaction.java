package com.example.merchant.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.merchant.model.TransactionEntity;
import com.example.merchant.model.TransactionStatus;
import com.example.merchant.model.UserEntity;
import com.example.merchant.request.TransactionRequest;

/**
 * Authorize transaction - has amount and used to hold customer's amount
 *
 */
public class AuthorizeTransaction extends BaseTransaction implements Transaction {

	private static final Logger logger = LoggerFactory.getLogger(ReversalTransaction.class);

	public AuthorizeTransaction(UserEntity userEntity) {
		super(userEntity);
	}

	@Override
	public TransactionEntity payment(TransactionRequest transactionRequest) {

		TransactionEntity result;
		result = super.payment(transactionRequest);
		result.setStatus(TransactionStatus.approved.name());
		logger.info("Authorize Transaction submited");
		return result;
	}

}
