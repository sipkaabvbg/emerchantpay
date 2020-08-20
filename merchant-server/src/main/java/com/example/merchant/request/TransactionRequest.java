package com.example.merchant.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TransactionRequest {

	@DecimalMin(value = "0.0")
	private BigDecimal amount;

	@Pattern(regexp = "^[0-9]{5,11}$")
	private String customer_phone;

	@NotBlank
	@Size(max = 40)
	@Email
	private String customer_email;

	private String referenceId;

	private String username;

	public TransactionRequest() {
	}

	public TransactionRequest(BigDecimal amount, String customer_phone, String customer_email, String referenceId,
			String username) {

		this.amount = amount;
		this.customer_phone = customer_phone;
		this.customer_email = customer_email;
		this.referenceId = referenceId;
		this.username = username;
	}


	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
