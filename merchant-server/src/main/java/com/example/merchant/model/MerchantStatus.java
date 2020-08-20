package com.example.merchant.model;

/**
 * This Enum sets Merchant status
 *
 */
public enum MerchantStatus {
	INACTIVE(0), ACTIVE(1);

	private int id;

	MerchantStatus(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
