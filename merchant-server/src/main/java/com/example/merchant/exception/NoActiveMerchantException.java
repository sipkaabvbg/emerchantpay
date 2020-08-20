package com.example.merchant.exception;

/**
 * Custom exception throws when merchant not active
 *
 */
public class NoActiveMerchantException extends RuntimeException {

	public NoActiveMerchantException(String message) {
		super(message);
	}

}
