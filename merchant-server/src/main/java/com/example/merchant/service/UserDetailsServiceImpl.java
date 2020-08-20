package com.example.merchant.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.merchant.converter.UserProfileConverter;
import com.example.merchant.exception.BadRequestException;
import com.example.merchant.exception.ResourceNotFoundException;
import com.example.merchant.model.UserEntity;
import com.example.merchant.repository.UserRepository;
import com.example.merchant.response.UserProfile;
import com.example.merchant.security.JwtTokenProvider;
import com.example.merchant.security.UserPrincipal;

/**
 * This class is service for User
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	/**
	 * Load UserDetails interface with username
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
		return UserPrincipal.create(user);
	}

	/**
	 * This method deletes uresr,actually changes the status of the user and it
	 * remains in the database for future reporting
	 * 
	 * @param loginRequest
	 * @return
	 */
	public String deleteUser(String username) {
		UserEntity userTemp;
		userTemp = getUserByUsername(username);
		if (userTemp.getTransactions().size()==0) {
			userRepository.delete(userTemp);
			logger.info("Deleted User with id:", userTemp.getId());
		}else {
			throw new BadRequestException("This user has transactions and cannot be deleted");
		}
		return username;
	}

	/**
	 * Load user by id
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public UserDetails loadUserById(Long id) {
		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		return UserPrincipal.create(user);
	}

	/**
	 * This method updates uresr.User name cannot be changed
	 * 
	 * @param user
	 * @return
	 */
	public UserProfile updateUser(UserProfile user) {
		UserEntity userTemp;
		userTemp = getUserByUsername(user.getUsername());
		userTemp.setDescription(user.getDescription());
		userTemp.setEmail(user.getEmail());
		userTemp.setName(user.getName());
		userTemp.setPassword(passwordEncoder.encode(user.getPassword()));
		userTemp.setStatus(user.getStatus());
		userTemp.setTotal_transaction_sum(user.getTotal_transaction_sum());
		userRepository.save(userTemp);
		return user;
	}

	/**
	 * This method returns users without passwords and id.Usen mane is unique and
	 * also can not be changed
	 * 
	 * @return
	 */
	public List<UserProfile> getUsers() {
		Iterable<UserEntity> iterableEntity = userRepository.findAll();
		List<UserProfile> result = new ArrayList<>();
		iterableEntity.forEach(userTypeEntity -> {
			// for security reason we delete password
			userTypeEntity.setPassword("");
			result.add(UserProfileConverter.toWrapper(userTypeEntity));
		});
		return result;
	}

	/**
	 * Load UserDetails interface with username
	 */
	public UserEntity getUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
		return user;
	}
	
	/**
	 * This method get user from JWT token
	 * @param request
	 * @return - UserPrincipal
	 */
	public UserPrincipal getUser(HttpServletRequest request) {
		String jwt = null;
		UserPrincipal userDetails = null;
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			jwt=bearerToken.substring(7, bearerToken.length());
		}
		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Long userId = tokenProvider.getUserIdFromJWT(jwt);

			userDetails = (UserPrincipal) loadUserById(userId);
		
	}
		return userDetails;
	}
}