package com.example.merchant.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.merchant.model.TransactionEntity;
import com.example.merchant.model.TransactionStatus;
import com.example.merchant.model.UserEntity;
import com.example.merchant.request.TransactionRequest;

/**
 * Error transaction - has invalid uuid
 *
 */
public class ErrorTransaction extends BaseTransaction implements Transaction {

	private static final Logger logger = LoggerFactory.getLogger(ErrorTransaction.class);

	public ErrorTransaction(UserEntity userEntity) {
		super(userEntity);
	}

	@Override
	public TransactionEntity payment(TransactionRequest transactionRequest) {

		TransactionEntity result;

		result = super.payment(transactionRequest);
		result.setStatus(TransactionStatus.error.name());
		result.setReferenceId(transactionRequest.getReferenceId());
		logger.info("Error Transaction submited");
		return result;
	}

}
