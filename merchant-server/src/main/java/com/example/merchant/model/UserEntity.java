package com.example.merchant.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

/**
 * This class represents User entity/Admin and Merchant/.
 */

@Entity

@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	@NotBlank
	@Size(max = 15)
	private String username;

	@NotBlank
	@Size(max = 100)
	private String password;

	@NotBlank
	@Size(max = 40)
	private String name;

	@NotBlank
	@Size(max = 1000)
	private String description;

	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotNull
	@Column(precision = 3, scale = 0)
	private Integer status;

	@Digits(integer = 9, fraction = 2)
	@DecimalMin("0.00")
	private BigDecimal total_transaction_sum;

	@NotBlank
	@Size(max = 15)
	private String role;

	@OneToMany(mappedBy = "user")
	private List<TransactionEntity> transactions;

	public UserEntity() {

	}

	/**
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param name
	 * @param description
	 * @param email
	 * @param status
	 * @param total_transaction_sum
	 * @param transactions
	 * @param role
	 */
	public UserEntity(String username, String password, String name, String description, String email, Integer status,
			BigDecimal total_transaction_sum, String role, List<TransactionEntity> transactions) {

		this.username = username;
		this.password = password;
		this.name = name;
		this.description = description;
		this.email = email;
		this.status = status;
		this.total_transaction_sum = total_transaction_sum;
		this.role = role;
		this.transactions = transactions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// this.password =passwordEncoder.encode(password);
		this.password = password;
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

	public List<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((total_transaction_sum == null) ? 0 : total_transaction_sum.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (total_transaction_sum == null) {
			if (other.total_transaction_sum != null)
				return false;
		} else if (!total_transaction_sum.equals(other.total_transaction_sum))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}