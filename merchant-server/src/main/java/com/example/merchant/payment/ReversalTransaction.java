package com.example.merchant.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.merchant.model.TransactionEntity;
import com.example.merchant.model.TransactionStatus;
import com.example.merchant.model.UserEntity;
import com.example.merchant.request.TransactionRequest;

/**
 * Reversal transaction - has no amount, used to invalidate the Authorize
 * Transaction
 * 
 */
public class ReversalTransaction extends BaseTransaction implements Transaction {

	private static final Logger logger = LoggerFactory.getLogger(ReversalTransaction.class);

	public ReversalTransaction(UserEntity userEntity) {
		super(userEntity);
	}

	@Override
	public TransactionEntity payment(TransactionRequest transactionRequest) {
		TransactionEntity result;

		result = super.payment(transactionRequest);
		result.setStatus(TransactionStatus.reversed.name());
		result.setReferenceId(transactionRequest.getReferenceId());
		logger.info("Reversal Transaction submited");
		return result;
	}

}
