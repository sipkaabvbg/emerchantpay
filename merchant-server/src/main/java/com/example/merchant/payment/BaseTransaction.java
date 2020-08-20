package com.example.merchant.payment;

import java.util.UUID;

import com.example.merchant.model.TransactionEntity;
import com.example.merchant.model.UserEntity;
import com.example.merchant.request.TransactionRequest;

/**
 * Base class for Transaction
 */
public class BaseTransaction {

	private UserEntity userEntity;

	public BaseTransaction(UserEntity userEntity) {
		super();
		this.userEntity = userEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public TransactionEntity payment(TransactionRequest transactionRequest) {
		String uuid;
		TransactionEntity result;

		uuid = UUID.randomUUID().toString().toUpperCase();
		result = new TransactionEntity();
		result.setAmount(transactionRequest.getAmount());
		result.setCustomer_email(transactionRequest.getCustomer_email());
		result.setCustomer_phone(transactionRequest.getCustomer_phone());
		result.setUser(getUserEntity());
		result.setUuid(uuid);
		return result;
	}
}
