package com.example.merchant.converter;

import org.springframework.beans.BeanUtils;

import com.example.merchant.model.UserEntity;
import com.example.merchant.response.UserProfile;

/**
 * This class converts UserEntity to response type
 *
 */
public class UserProfileConverter {
	public static UserProfile toWrapper(UserEntity userEntity) {
		UserProfile userProfileWrapper = new UserProfile();
		BeanUtils.copyProperties(userEntity, userProfileWrapper);
		return userProfileWrapper;
	}
}
