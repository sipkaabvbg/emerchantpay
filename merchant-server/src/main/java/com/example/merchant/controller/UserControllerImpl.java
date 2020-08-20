package com.example.merchant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.merchant.api.UserController;
import com.example.merchant.response.UserProfile;
import com.example.merchant.security.JwtTokenProvider;
import com.example.merchant.security.UserPrincipal;
import com.example.merchant.service.UserDetailsServiceImpl;

/**
 * This class represents User controller and User/Merchant functionality,
 * display, edit, delete merchants Controllers are implemented according task 7.
 * For controllers, try to: Keep them thin Encapsulate business logic in service
 * objects
 * 
 * @author i3
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

	/**
	 * This method deletes users, actually changes the status of the user and it
	 * remains in the database for future reporting
	 * 
	 * @param loginRequest
	 * @return
	 */
	public String deleteUser(@PathVariable("username") String username) {
		return userDetailsServiceImpl.deleteUser(username);
	}

	/**
	 * This method updates uresr.User name cannot be changed
	 * 
	 * @param user
	 * @return
	 */
	public UserProfile updateUser(@Valid @RequestBody UserProfile user) {
		return userDetailsServiceImpl.updateUser(user);
	}

	/**
	 * This method returns users without passwords and id.Usen mane is unique and
	 * also can not be changed
	 * 
	 * @return
	 */
	public List<UserProfile> getUsers() {
		return userDetailsServiceImpl.getUsers();
	}

	/**
	 * This method get user from JWT token
	 * @param request
	 * @return - UserPrincipal
	 */
	@Override
	public UserPrincipal getUser(HttpServletRequest request) {
		return userDetailsServiceImpl.getUser(request);
	}
}
