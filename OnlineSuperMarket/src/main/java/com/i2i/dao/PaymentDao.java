/**
 * 
 */
package com.i2i.dao;

import java.util.List;

import com.i2i.exception.ApplicationException;
import com.i2i.model.Payment;

/**
 * <h1>PurchaseOrderDao</h1>
 * <p>Performs all purchaseorder related database tasks using hibernate.<p>
 * @author Kumar Albert
 *
 * version 1.0
 * 
 */
public interface PaymentDao extends GenericDao<Payment, Long> {
	
	/**
	 * Inserts payment details into database.
	 * @param payment
	 *     payment object that has to be inserted into database.
     * @return True
     *     If payment object is inserted.	
     * @throws ApplicationException
	 *     If there is any interruption occurred in the database.
	 */
    public boolean insertPaymentDetails(Payment payment) throws ApplicationException;
	    
    /**
     * Retrieves the payment list present in the database.
     * @return List<payment>
     *     List of payment objects to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */ 
    public List<Payment> retrievePaymentDetails() throws ApplicationException;
}
