package com.example.merchant.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.merchant.model.UserEntity;

/**
 * This class represents User processor
 *
 */
public class UserProcessor implements ItemProcessor<UserEntity, UserEntity> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserProcessor.class);

	public UserEntity process(UserEntity user) throws Exception {
		LOGGER.info("Inserting user '{}'", user);
		return user;
	}
}