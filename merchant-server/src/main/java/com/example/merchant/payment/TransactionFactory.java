package com.example.merchant.payment;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.merchant.exception.ApplicationException;
import com.example.merchant.exception.NoActiveMerchantException;
import com.example.merchant.model.MerchantStatus;
import com.example.merchant.model.TransactionEntity;
import com.example.merchant.model.TransactionStatus;
import com.example.merchant.model.UserEntity;
import com.example.merchant.repository.TransactionRepository;
import com.example.merchant.request.TransactionRequest;
import com.example.merchant.service.UserDetailsServiceImpl;

/**
 * Transaction Types Authorize transaction - has amount and used to hold
 * customer'samount Charge transaction - has amount and used to confirm the
 * amount is taken from the customer's account and transferred to the merchant
 * The merchant's total transactions amount has to be the sum of the approved
 * Charge transactions Refund transaction - has amount and used to reverse a
 * specific amount (whole amount) of the Charge Transaction and return it to the
 * customer Transitions the Charge transaction to status refunded The approved
 * Refund transactions will decrease the merchant's total transaction amount
 * Reversal transaction - has no amount, used to invalidate the Authorize
 * Transaction Transitions the Authorize transaction to status reversed
 *
 */

public class TransactionFactory {

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	/**
	 * Use getTransaction method to get object of type Transaction
	 * Implements main application logic 
	 * 
	 * @param transactionRequest
	 * @return
	 */

	public Transaction getTransaction(TransactionRequest transactionRequest) {
		String uuid;
		UserEntity userDetails;
		TransactionEntity transactionEntity;
		TransactionEntity transactionEntityCheckedUuid;

		if (transactionRequest == null) {
			throw new ApplicationException("Refresh application, wrong state!");
		}
		// check user is valid
		userDetails = userDetailsServiceImpl.getUserByUsername(transactionRequest.getUsername());
		// check user is inactive
		if (userDetails.getStatus() == (MerchantStatus.INACTIVE.getID())) {
			throw new NoActiveMerchantException(
					"No transactions can be submitted unless the merchant is in active state!");
		}
		transactionEntity = transactionRepository.findByUuid(transactionRequest.getReferenceId());
		//if(transactionEntity==null) {
		//	throw new ApplicationException("Wrong Transaction uuid!");
		//}
		if (transactionEntity!=null&&transactionEntity.getStatus().equals(TransactionStatus.error.name())) {
			throw new ApplicationException("Not allowed to process the Transaction with the status error!");
		}
		if (transactionEntity!=null&&transactionEntity.getStatus().equals(TransactionStatus.reversed.name())) {
			throw new ApplicationException("Not allowed to process the Transaction with the status reversed!");
		}
		if (transactionEntity!=null&&transactionEntity.getStatus().equals(TransactionStatus.refunded.name())) {
			throw new ApplicationException("Not allowed to process the Transaction with the status refunded!");
		}
		
		//case: AuthorizeTransaction
		if (transactionRequest.getReferenceId() == null || transactionRequest.getReferenceId().isBlank()) {
			System.out.println("!!!!!AuthorizeTransaction");
			return new AuthorizeTransaction(userDetails);
		}
 
	    //case:	ReversalTransaction transactionEntity.getAmount().compareTo(BigDecimal.ZERO) > 0||
		if ((transactionRequest.getAmount()==null ||transactionRequest.getAmount().compareTo(BigDecimal.ZERO) == 0
		    &&(transactionEntity.getAmount().compareTo(BigDecimal.ZERO) >= 0))
		    &&(transactionRequest.getReferenceId()!=null||!transactionRequest.getReferenceId().isBlank())
		    &&transactionEntity.getReferenceId()==null)
		     {
			 return new ReversalTransaction(userDetails);
		}
		if(transactionEntity==null) {
			throw new ApplicationException("Refresh application, wrong state!");
		}else {
		uuid =	transactionEntity.getUuid();
		}
		transactionEntityCheckedUuid=transactionRepository.findByReferenceId(uuid);
		
		//case:	ChargeTransaction
        if (transactionEntity.getStatus().equals(TransactionStatus.approved.name())
				&& transactionEntity.getReferenceId() == null
				&& transactionEntityCheckedUuid==null) {
			 if(!(transactionEntity.getAmount().compareTo(transactionRequest.getAmount())==0)) {
				return new ErrorTransaction(userDetails);
			}
			return new ChargeTransaction(userDetails);
		}
        
		//case:	RefundTransaction
		if (transactionEntity.getStatus().equals(TransactionStatus.approved.name())
				&& transactionEntity.getReferenceId() != null) {
			 if(!(transactionEntity.getAmount().compareTo(transactionRequest.getAmount())==0)) {
				return new ErrorTransaction(userDetails);
			}
			return new RefundTransaction(userDetails);
		}

		//case: ErrorTransaction
		return new ErrorTransaction(userDetails);
	}
}
