/**
 * 
 */
package com.i2i.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.i2i.dao.CartDao;
import com.i2i.exception.ApplicationException;
import com.i2i.model.Cart;

/**
 * </h1>CartDao</h1>
 * <p>Performs all cart related database tasks using hibernate.<p>
 * @author Kumar Albert
 *
 * version 1.0
 * 
 */
@Repository("cartDao")
@Transactional
public class CartDaoHibernate extends GenericDaoHibernate<Cart, Long> implements CartDao{
	
	public CartDaoHibernate() {
        super(Cart.class);
    }
	/**
	 * Inserts cart details into the database.
	 * @param cart
	 *     cart object to be inserted.
	 * @return True
	 *     If cart objest is inserted.
	 * @throws ApplicationException
	 *      If there is any interruption occurred in the database.
	 */
    public boolean insertCart(Cart cart) throws ApplicationException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(cart);
        	session.flush();
            transaction.commit();
            session.clear();
            return true;
        } catch (HibernateException e) {
        	e.printStackTrace();
            throw new ApplicationException("Some error occured while inserting details of Cart: "
                                            +cart.getId(),e);
        } 
    }
    
    /**
     * Retrieves the category list present in the database.
     * @return List<Cart>
     *     List of cart objects to be returned.
     * @throws ApplicationException
     *    If there is any interruption occurred in the database.
     */
    @SuppressWarnings("unchecked")
	public List<Cart> retrieveCartDetails() throws ApplicationException {
    	Session session = null;
        try {
            session = getSession();
            return session.createQuery("FROM Cart").list(); 
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while listing the details of all cart",e); 
        } finally {
        	session.flush();
        }
    }
    
    /**
     * Retrieves cart object for the given orderid.
     * @param orderId
     *     orderid of the cart to be found.
     * @return cart
     *     cart object to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public Cart searchCartByOrderId(int orderId) throws ApplicationException {
    	Session session = null;
        try {
            session = getSession();
            return (Cart)session.get(Cart.class, orderId);
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while viewing details of cart",e); 
        } finally {
        	session.flush();
        }  
    }

    /**
     * Retrieves cart object for the given id.
     * @param id
     *     id of the cart to be found.
     * @return cart
     *     cart object to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public Cart searchCartById(int id) throws ApplicationException {
    	Transaction transaction = null;
    	Session session = null;
        try {
            session = getSession();
    	   	transaction = session.beginTransaction();
	      	String sql = "SELECT * FROM Cart WHERE id = :id";
	      	SQLQuery query = session.createSQLQuery(sql);
	      	query.addEntity(Cart.class);
	      	query.setParameter("id", id);
	      	Object object = query.list().get(0);
	      	Cart cartFromDao = (Cart)object;
	      	transaction.commit();
	      	return cartFromDao;
    	} catch (HibernateException e) {
    		e.printStackTrace();
    		throw new ApplicationException("Some error occured while viewing details of "
    				                        +id,e); 
    	} finally {
        	session.flush();
    	}  
    }
    
    /**
     * Updates the cart object into the database.
     * @param cart
     *     cart object to be updated.
     * @return True
     *     If category object is updated.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean updateCart(Cart cart) throws ApplicationException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSession();
        	System.out.println(cart);
        	session.flush();
            transaction = session.beginTransaction();
            session.update(cart); 
            transaction.commit();
            session.clear();
            return true;
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while updating details of "+ 
            		                        cart.getId(),e); 
        } 
    }
    
    /**
     * Deletes entire cart object from the database.
     * @param cart
     *     cart object to be deleted.
     * @return True 
     *     If cart object is deleted.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean deleteCart(Cart cart) throws ApplicationException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSession();
        	System.out.println(cart);
        	System.out.println(session);
            transaction = session.beginTransaction();
            session.delete(cart); 
            transaction.commit();
            return true;
        } catch (HibernateException e) {
        	e.printStackTrace();
            throw new ApplicationException("Some error occured while deleting the details of "+
            		                        cart.getId(),e); 
        } finally {
        	session.flush();
        }	
    }
}
