/**
 * 
 */
package com.i2i.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import com.i2i.exception.ApplicationException;
import com.i2i.model.Product;
/**
 * <h1>ProductDao</h1>
 * <p>Performs all product related database tasks using hibernate.<p>
 * @author Mukilan.K
 *
 * @version 1.0
 */
public interface ProductDao extends GenericDao<Product, Long> {
    
	/**
	 * Inserts product details into database.
	 * @param product
	 *     product object that has to be inserted into database.
     * @return True
     *     If product object is inserted.	
     * @throws ApplicationException
	 *     If there is any interruption occurred in the database.
	 */
    public boolean insertProduct(Product product) throws ApplicationException ;
    
    /**
     * Retrieves the product list present in the database.
     * @return List<Product>
     *     List of product objects to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */  
    public List<Product> retrieveProductDetails() throws ApplicationException ;

    /**
     * Retrieves product object for the given name.
     * @param name
     *     name of the product to be found.
     * @return product
     *     product object to be retrieved.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public Product searchProductByName(String name) throws ApplicationException ;
    /**
     * Updates the product object into the database.
     * @param product
     *     product object to be updated.
     * @return True
     *     If product object is updated.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean updateProduct(Product product) throws ApplicationException;

    /**
     * Deletes entire product object from the database.
     * @param product
     *     product object to be deleted.
     * @return True 
     *     If product object is deleted.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */    
    public boolean deleteProduct(Product product) throws ApplicationException;
}