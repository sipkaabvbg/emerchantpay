package com.example.merchant.payment;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.merchant.model.TransactionEntity;
import com.example.merchant.model.TransactionStatus;
import com.example.merchant.model.UserEntity;
import com.example.merchant.request.TransactionRequest;

/**
 * Charge transaction - has amount and used to confirm the amount is taken from
 * the customer's account and transferred to the merchant The merchant's total
 * transactions amount has to be the sum of the approved Charge transactions
 *
 */
public class ChargeTransaction extends BaseTransaction implements Transaction {

	private static final Logger logger = LoggerFactory.getLogger(ChargeTransaction.class);

	public ChargeTransaction(UserEntity userEntity) {
		super(userEntity);
	}

	@Override
	public TransactionEntity payment(TransactionRequest transactionRequest) {
		BigDecimal amount;
		TransactionEntity result;

		result = super.payment(transactionRequest);
		result.setStatus(TransactionStatus.approved.name());
		result.setReferenceId(transactionRequest.getReferenceId());
		amount = getUserEntity().getTotal_transaction_sum().add(transactionRequest.getAmount());
		getUserEntity().setTotal_transaction_sum(amount);
		logger.info("Charge Transaction submited "+amount+" !! "+getUserEntity().getTotal_transaction_sum());
		return result;
	}

}
