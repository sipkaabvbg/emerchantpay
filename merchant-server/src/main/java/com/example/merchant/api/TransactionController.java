package com.example.merchant.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.merchant.request.TransactionRequest;
import com.example.merchant.response.TransactionResponse;

import io.swagger.annotations.ApiOperation;

public interface TransactionController {

	/**
	 * This method post Transaction
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "This method upload transaction")
	@RequestMapping(value = "/transaction", method = RequestMethod.POST,
	consumes = {"application/json", "application/xml"},
	produces = { "application/json", "application/xml" })
	public TransactionResponse postTransaction(@Valid @RequestBody TransactionRequest transaction);

	/**
	 * This method find all transactions for concrete merchant
	 * 
	 * @return
	 */
	@ApiOperation(value = "This method find all transactions for concrete merchant")
	@RequestMapping(value = "/transaction/{username}", method = RequestMethod.GET,
	consumes = {"application/json", "application/xml"},
	produces = { "application/json", "application/xml" })
	public List<TransactionResponse> getTransactions(@PathVariable("username") String username);

}
