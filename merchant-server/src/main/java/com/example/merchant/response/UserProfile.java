package com.example.merchant.response;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * This class represents wrapper class that returns response object
 *
 */

public class UserProfile {

	private String username;
	private String password;
	private String name;
	private String description;
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;
	@Min(0)
	@Max(1)
	private Integer status;
	private BigDecimal total_transaction_sum;
	private String role;

	public UserProfile() {
	}

	public UserProfile(String username, String password, String name, String description, String email, Integer status,
			BigDecimal total_transaction_sum, String role) {

		this.username = username;
		this.password = password;
		this.name = name;
		this.description = description;
		this.email = email;
		this.status = status;
		this.total_transaction_sum = total_transaction_sum;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getTotal_transaction_sum() {
		return total_transaction_sum;
	}

	public void setTotal_transaction_sum(BigDecimal total_transaction_sum) {
		this.total_transaction_sum = total_transaction_sum;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
