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

import com.i2i.dao.ProductDao;
import com.i2i.exception.ApplicationException;
import com.i2i.model.Category;
import com.i2i.model.Product;
import com.i2i.model.User;
/**
 * <h1>ProductDao</h1>
 * <p>Performs all product related database tasks using hibernate.<p>
 * @author Mukilan.K
 *
 * @version 1.0
 */
@Repository("productDao")
@Transactional
public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {

	 public ProductDaoHibernate() {
	        super(Product.class);
	    }

		/**
		 * Inserts product details into database.
		 * @param product
		 *     product object that has to be inserted into database.
	     * @return True
	     *     If product object is inserted.	
	     * @throws ApplicationException
		 *     If there is any interruption occurred in the database.
		 */
	    public boolean insertProduct(Product product) throws ApplicationException {
	        Transaction transaction = null;
			Session session = null;
	        try {
	    		session = getSession();
	            transaction = session.beginTransaction();
	            session.save(product);
	            transaction.commit();
	            return true;
	        } catch (HibernateException e) {
	            throw new ApplicationException("Some error occured while inserting details of product: "
	                                            +product.getName(),e);
	        } finally {
	            session.flush(); 
	        }
	    }
	    
    /**
     * Retrieves the product list present in the database.
     * @return List<Product>
     *     List of product objects to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */  
	@SuppressWarnings("unchecked")
	public List<Product> retrieveProductDetails() throws ApplicationException {
		Session session = null;
		try {
    		session = getSession();
        	System.out.println("**************************");
        	System.out.println(session);
        	System.out.println("**************************");
            return session.createQuery("FROM Product").list(); 
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while listing the details of all Products",e); 
        } finally {
            session.flush(); 
        }
    }

    /**
     * Retrieves product object for the given name.
     * @param name
     *     name of the product to be found.
     * @return product
     *     product object to be retrieved.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public Product searchProductByName(String name) throws ApplicationException {
        Transaction transaction = null;
		Session session = null;
        try {
    		session = getSession();
    		System.out.println(name);
    		session.flush();
    		transaction = session.beginTransaction();
            String sql = "SELECT * FROM Product WHERE name = :name";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Product.class);
            query.setParameter("name", name);
            Object object = query.list().get(0);
            Product productFromDao = (Product)object;
        	System.out.println(productFromDao);
            transaction.commit();
            session.clear();
            return productFromDao;
         } catch (HibernateException e) {
        	 e.printStackTrace();
            throw new ApplicationException("Some error occured while viewing details of "
                                            +name,e); 
        }
    }
    
    /**
     * Updates the product object into the database.
     * @param product
     *     product object to be updated.
     * @return True
     *     If product object is updated.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean updateProduct(Product product) throws ApplicationException {
        Transaction transaction = null;
        Session session = null;
        try {
    		session = getSession();
            transaction = session.beginTransaction();
            session.update(product); 
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while updating details of "+ 
            		                        product.getName(),e); 
        } finally {
        	session.flush();
        }
    }
    
    /**
     * Deletes entire product object from the database.
     * @param product
     *     product object to be deleted.
     * @return True 
     *     If product object is deleted.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */    
    public boolean deleteProduct(Product product) throws ApplicationException {
        Transaction transaction = null;
        Session session = null;
        try {
    		session = getSession();
            transaction = session.beginTransaction();
            session.delete(product); 
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while deleting the details of "+
            		                        product.getName(),e); 
        } finally {
            session.flush(); 
        }	
    }
}