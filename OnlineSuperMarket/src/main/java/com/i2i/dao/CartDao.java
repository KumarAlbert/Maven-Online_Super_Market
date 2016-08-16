/**
 * 
 */
package com.i2i.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.i2i.exception.ApplicationException;
import com.i2i.model.Cart;
import com.i2i.model.Category;

/**
 * </h1>CartDao</h1>
 * <p>Performs all cart related database tasks using hibernate.<p>
 * @author Kumar Albert
 *
 * version 1.0
 * 
 */
public interface CartDao extends  GenericDao<Cart, Long>{
	
	/**
	 * Inserts cart details into the database.
	 * @param cart
	 *     cart object to be inserted.
	 * @return True
	 *     If cart objest is inserted.
	 * @throws ApplicationException
	 *      If there is any interruption occurred in the database.
	 */
    public boolean insertCart(Cart cart) throws ApplicationException;
    
    /**
     * Retrieves the category list present in the database.
     * @return List<Cart>
     *     List of cart objects to be returned.
     * @throws ApplicationException
     *    If there is any interruption occurred in the database.
     */
	public List<Cart> retrieveCartDetails() throws ApplicationException ;
    
    /**
     * Retrieves cart object for the given orderid.
     * @param orderId
     *     orderid of the cart to be found.
     * @return cart
     *     cart object to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public Cart searchCartByOrderId(int orderId) throws ApplicationException;

    /**
     * Retrieves cart object for the given id.
     * @param id
     *     id of the cart to be found.
     * @return cart
     *     cart object to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public Cart searchCartById(int id) throws ApplicationException;
    
    /**
     * Updates the cart object into the database.
     * @param cart
     *     cart object to be updated.
     * @return True
     *     If category object is updated.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean updateCart(Cart cart) throws ApplicationException;
    
    /**
     * Deletes entire cart object from the database.
     * @param cart
     *     cart object to be deleted.
     * @return True 
     *     If cart object is deleted.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean deleteCart(Cart cart) throws ApplicationException;
}
