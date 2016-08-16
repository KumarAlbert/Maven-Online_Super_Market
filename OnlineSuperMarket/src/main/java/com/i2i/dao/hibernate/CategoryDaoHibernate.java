/**
 * 
 */
package com.i2i.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import com.i2i.model.Category;
import com.i2i.dao.CategoryDao;
import com.i2i.exception.ApplicationException;
/**
 * <h1>CategoryDao</h1>
 * <p>Performs all user related database tasks using hibernate.<p>
 * @author Mukilan.K
 *
 * @version 1.0
 */
@Repository ("categoryDao")
@Transactional
public class CategoryDaoHibernate extends GenericDaoHibernate<Category, Long> implements CategoryDao {

		
	public CategoryDaoHibernate() {
        super(Category.class);
    }
	/**
	 * Inserts category details into database.
	 * @param category
	 *     category object that has to be inserted into database.
     * @return True
     *     If category object is inserted.	
     * @throws ApplicationException
	 *     If there is any interruption occurred in the database.
	 */
    public boolean insertCategory(Category category) throws ApplicationException { 
    	Session session = null;
        try {
            session = getSession();
            System.out.println(session);
            System.out.println("inside dao");
            session.save(category);
            session.saveOrUpdate(category);
            return true;
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while inserting details of category: "
                                            +category.getName(),e);
        } finally {
        	session.flush();
        }
    }
    
    /**
     * Retrieves the category list present in the database.
     * @return List<Category>
     *     List of category objects to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
	@SuppressWarnings("unchecked")
	public List<Category> retrieveCategoryDetails() throws ApplicationException {
		Session session = null;
		session = getSession();
        try {
            return session.createQuery("FROM Category").list(); 
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while listing the details of all categories",e); 
        } finally {
            session.flush(); 
        }
    }
    
    /**
     * Retrieves Category object for the given name.
     * @param name
     *     name of the category to be found.
     * @return Category
     *     category object to be retrieved.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
	@SuppressWarnings("unchecked")
	public Category searchCategoryByName(String name) throws ApplicationException {
		Session session = null;
        try {
        	session = getSession();
            String sql = "SELECT * FROM Category WHERE name = :name";
		    SQLQuery query = session.createSQLQuery(sql);
		    query.addEntity(Category.class);
		    query.setParameter("name", name);
		    List<Category> results = query.list();
                for(Category category : results){
        	        return category;
                }
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while viewing details of "
                                            +name,e); 
        } finally {
            session.flush();
        }  
        return null;
    }
    
    /**
     * Updates the category object into the database.
     * @param category
     *     category object to be updated.
     * @return True
     *     If category object is updated.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean updateCategory(Category category) throws ApplicationException {
    	Session session = null;
        try {
        	session = getSession();
            session.saveOrUpdate(category);
            return true;
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while updating details of "+ 
            		                        category.getName(),e); 
        } finally {
            session.flush();
        }
    }
    
    /**
     * Deletes entire category object from the database.
     * @param category
     *     category object to be deleted.
     * @return True 
     *     If category object is deleted.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean deleteCategory(Category category) throws ApplicationException {
    	Session session = null;
        try {
        	session = getSession();
            session.delete(category); 
            return true;
        } catch (HibernateException e) {
            throw new ApplicationException("Some error occured while deleting the details of "+
            		                        category.getName(),e); 
        } finally {
            session.flush();
        }	
    }
}