package com.example.merchant.repository;

import com.example.merchant.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This repository manage Users.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	List<UserEntity> findByIdIn(List<Long> userIds);

	Optional<UserEntity> findByUsername(String username);

	Boolean existsByUsername(String username);

	Long deleteByUsername(String name);
}
