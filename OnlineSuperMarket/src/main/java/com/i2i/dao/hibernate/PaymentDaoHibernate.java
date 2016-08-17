/**
 * 
 */
package com.i2i.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.i2i.dao.PaymentDao;
import com.i2i.dao.PurchaseOrderDao;
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
@Repository("paymentDao")
public class PaymentDaoHibernate extends GenericDaoHibernate<Payment, Long> implements PaymentDao {
	
	public PaymentDaoHibernate() {
        super(Payment.class);
    }
	/**
	 * Inserts payment details into database.
	 * @param payment
	 *     payment object that has to be inserted into database.
     * @return True
     *     If payment object is inserted.	
     * @throws ApplicationException
	 *     If there is any interruption occurred in the database.
	 */
    public boolean insertPaymentDetails(Payment payment) throws ApplicationException {
	    Transaction transaction = null;
	    Session session = null;
        try {
            session = getSession();
	        session.flush();
	        transaction = session.beginTransaction();
	        System.out.println(payment);
	        session.save(payment);
	        System.out.println(session.save(payment));
	        transaction.commit();
	        session.clear();
	        return true;
	    } catch (HibernateException e) {
	    	e.printStackTrace();
	        throw new ApplicationException("Some error occured while inserting details of Cart: "
	                                        +payment.getId(),e);
	    }
	}
	    
    /**
     * Retrieves the payment list present in the database.
     * @return List<payment>
     *     List of payment objects to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */ 
    @SuppressWarnings("unchecked")
	public List<Payment> retrievePaymentDetails() throws ApplicationException {
    	Session session = null;
        try {
            session = getSession();
	        return session.createQuery("FROM PurchaseOrder").list(); 
	    } catch (HibernateException e) {
	        throw new ApplicationException("Some error occured while listing the details of all cart",e); 
	     } finally {
	    	 session.flush();
	     }
    }

}
