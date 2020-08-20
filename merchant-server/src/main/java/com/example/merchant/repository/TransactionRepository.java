package com.example.merchant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.merchant.model.TransactionEntity;

/**
 * This repository manage Users.
 */
@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

	TransactionEntity findByUuid(String uuid);

	List<TransactionEntity> findAllByUserUsername(String username);
	
	TransactionEntity findByReferenceId(String uuid);

}
