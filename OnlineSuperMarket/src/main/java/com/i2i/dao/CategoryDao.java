/**
 * 
 */
package com.i2i.dao;

import java.util.List;

import com.i2i.model.Category;
import com.i2i.exception.ApplicationException;
/**
 * <h1>CategoryDao</h1>
 * <p>Performs all user related database tasks using hibernate.<p>
 * @author Mukilan.K
 *
 * @version 1.0
 */
public interface CategoryDao extends GenericDao<Category, Long> {

	/**
	 * Inserts category details into database.
	 * @param category
	 *     category object that has to be inserted into database.
     * @return True
     *     If category object is inserted.	
     * @throws ApplicationException
	 *     If there is any interruption occurred in the database.
	 */
    public boolean insertCategory(Category category) throws ApplicationException;
    
    /**
     * Retrieves the category list present in the database.
     * @return List<Category>
     *     List of category objects to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
	public List<Category> retrieveCategoryDetails() throws ApplicationException;
    
    /**
     * Retrieves Category object for the given name.
     * @param name
     *     name of the category to be found.
     * @return Category
     *     category object to be retrieved.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
	public Category searchCategoryByName(String name) throws ApplicationException;
    
    /**
     * Updates the category object into the database.
     * @param category
     *     category object to be updated.
     * @return True
     *     If category object is updated.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean updateCategory(Category category) throws ApplicationException;
    
    /**
     * Deletes entire category object from the database.
     * @param category
     *     category object to be deleted.
     * @return True 
     *     If category object is deleted.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean deleteCategory(Category category) throws ApplicationException;
}