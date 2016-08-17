/**
 * 
 */
package com.i2i.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.dao.PaymentDao;
import com.i2i.exception.ApplicationException;
import com.i2i.model.Payment;
import com.i2i.model.User;
/**
 * <h1> Purchase Order Service </h1>
 * <p> It provides purchase order related services. </p>
 * @author Kumar Albert
 *
 * version 1.0
 * 
 */
@Service
public class PaymentService {
	
	@Autowired
	PaymentDao paymentDao;
    
	/**
	 * <p> This method used to add purchase order.</p>
	 * @param user This holds user object.
	 * @param total This holds purchase order's net value.
	 * @param paymentType This holds purchase order's payment type.
	 * @param status This holds purchase order's status.
	 * @param createdBy This holds id of who create purchase order.
	 * @param cartList This holds list of products.
	 * @return boolean This returns true, if operation successfully done.
	 * 					Otherwise it returns false.
	 * @throws ApplicationException
	
	 *//*
	public boolean addPaymentDetails(User user,String paymentType, Long cardNumber, String nameOnCard, int expiryMonth, 
			int expiryYear, int cvvNumber) throws ApplicationException {
        System.out.println(user);
        Payment payment = new Payment(user, paymentType, cardNumber, nameOnCard, expiryMonth, expiryYear, cvvNumber);
        paymentDao.insertPaymentDetails(payment);
        return true;
	}
	*/
	public boolean addPaymentDetails(Payment payment)throws ApplicationException{
        paymentDao.insertPaymentDetails(payment);
        return true;

	}
	
	/**
	 * <p> This method used to get purchase oreder list </p>
	 * @return List .It returns purchase order list. 
	 * @throws ApplicationException
	 */
	public List<Payment> getPurchaseOrderDetails() throws ApplicationException {
		return paymentDao.retrievePaymentDetails();
	}	
}
