package com.example.merchant.payment;

import com.example.merchant.model.TransactionEntity;
import com.example.merchant.request.TransactionRequest;

/**
 * In Factory pattern, we create object without exposing the creation logic to
 * the client and refer to newly created object using a common interface This
 * interface is core Factory interface
 */
public interface Transaction {

	TransactionEntity payment(TransactionRequest transactionRequest);
}
