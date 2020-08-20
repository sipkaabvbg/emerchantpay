package com.example.merchant.converter;

import org.springframework.beans.BeanUtils;

import com.example.merchant.model.TransactionEntity;
import com.example.merchant.response.TransactionResponse;

/**
 * This class converts TransactionEntity to response type
 *
 */
public class TransactionResponseConverter {

	public static TransactionResponse toWrapper(TransactionEntity transactionEntity) {
		TransactionResponse transactionWrapper = new TransactionResponse();
		BeanUtils.copyProperties(transactionEntity, transactionWrapper);
		return transactionWrapper;
	}
}
