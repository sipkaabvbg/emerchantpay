package com.example.merchant.response;

import java.math.BigDecimal;

public class TransactionResponse {

	private String uuid;

	private BigDecimal amount;

	private String status;

	private String customer_phone;
 
	private String customer_email;

	private String referenceId;

	public TransactionResponse() {
	}

	public TransactionResponse(String uuid, BigDecimal amount, String status, String customer_phone,
			String customer_email, String referenceId) {
		super();
		this.uuid = uuid;
		this.amount = amount;
		this.status = status;
		this.customer_phone = customer_phone;
		this.customer_email = customer_email;
		this.referenceId = referenceId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


}
