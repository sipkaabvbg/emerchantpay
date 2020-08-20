package com.example.merchant.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.merchant.response.UserProfile;
import com.example.merchant.security.UserPrincipal;

import io.swagger.annotations.ApiOperation;

public interface UserController {

	/**
	 * This method deletes uresr,actually changes the status of the user and it
	 * remains in the database for future reporting
	 * 
	 * @param loginRequest
	 * @return
	 */
	@ApiOperation(value = "This method deletes uresr")
	@RequestMapping(value = "/delete/{username}", method = RequestMethod.DELETE,
	consumes = {"application/json", "application/xml"},
	produces = { "application/json", "application/xml" })
	public String deleteUser(@PathVariable("username") String username);

	/**
	 * This method updates uresr.User name cannot be changed
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "This method updates uresr.User name cannot be changed. All fields in the object must be valid, even the email address")
	@RequestMapping(value = "/update", method = RequestMethod.POST,
	consumes = {"application/json", "application/xml"},
	produces = { "application/json", "application/xml" })
	public UserProfile updateUser(@Valid @RequestBody UserProfile user);

	/**
	 * This method returns users without passwords and id.Usen mane is unique and
	 * also can not be changed
	 * 
	 * @return
	 */
	@ApiOperation(value = "This method returns uresrs without passwords and id.Usen mane is unique and also can not be changed")
	@RequestMapping(value = "/users", method = RequestMethod.GET,
	consumes = {"application/json", "application/xml"},
	produces = { "application/json", "application/xml" })
	public List<UserProfile> getUsers();
	
	/**
	 * This method returns user  
	 * @return
	 */
	@ApiOperation(value = "This method returns uresr ")
	@RequestMapping(value = "/user", method = RequestMethod.GET,
	consumes = {"application/json", "application/xml"},
	produces = { "application/json", "application/xml" })
	public UserPrincipal getUser(HttpServletRequest request);
}
