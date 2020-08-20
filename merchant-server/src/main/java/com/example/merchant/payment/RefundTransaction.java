package com.example.merchant.payment;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.merchant.model.TransactionEntity;
import com.example.merchant.model.TransactionStatus;
import com.example.merchant.model.UserEntity;
import com.example.merchant.request.TransactionRequest;

/**
 * Refund transaction - has amount and used to reverse a specific amount (whole
 * amount) of the Charge Transaction and return it to the customer Transitions
 * the Charge transaction to status refunded The approved Refund transactions
 * will decrease the merchant's total transaction amount
 *
 */
public class RefundTransaction extends BaseTransaction implements Transaction {

	private static final Logger logger = LoggerFactory.getLogger(RefundTransaction.class);

	public RefundTransaction(UserEntity userEntity) {
		super(userEntity);
	}

	@Override
	public TransactionEntity payment(TransactionRequest transactionRequest) {
		BigDecimal amount;
		TransactionEntity result;

		result = super.payment(transactionRequest);
		result.setStatus(TransactionStatus.refunded.name());
		result.setReferenceId(transactionRequest.getReferenceId());
		amount = getUserEntity().getTotal_transaction_sum().subtract(transactionRequest.getAmount());
		getUserEntity().setTotal_transaction_sum(amount);
		logger.info("Refund Transaction submited");
		return result;
	}
}
