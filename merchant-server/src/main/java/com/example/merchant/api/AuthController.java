package com.example.merchant.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.merchant.request.LoginRequest;

import io.swagger.annotations.ApiOperation;

public interface AuthController {
	/**
	 * Login returns JWT
	 * @param loginRequest
	 * @return
	 */

	@ApiOperation(value = "This method login uresrs in application")

	@RequestMapping(value = "/login", method = RequestMethod.POST,
	consumes = {"application/json", "application/xml"},
	produces = { "application/json", "application/xml" })
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);

}
