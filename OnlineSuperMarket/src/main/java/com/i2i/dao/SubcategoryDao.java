/**
 * 
 */
package com.i2i.dao;

import java.util.List;

import com.i2i.model.Subcategory;
import com.i2i.exception.ApplicationException;
/**
 * <h1>SubcategoryDao</h1>
 * <p>Performs all subcategory related database tasks using hibernate.<p>
 * @author Mukilan.K
 *
 * @version 1.0
 */

public interface SubcategoryDao {

	/**
	 * Inserts subcategory details into database.
	 * @param subcategory
	 *     subcategory object that has to be inserted into database.
     * @return True
     *     If subcategory object is inserted.	
     * @throws ApplicationException
	 *     If there is any interruption occurred in the database.
	 */
    public boolean insertSubcategory(Subcategory subcategory) throws ApplicationException;
    
    /**
     * Retrieves the subcategory list present in the database.
     * @return List<Subcategory>
     *     List of subcategory objects to be returned.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */ 
    public List<Subcategory> retrieveSubcategoryDetails() throws ApplicationException;
    
    /**
     * Retrieves subcategory object for the given name.
     * @param name
     *     name of the subcategory to be found.
     * @return subcategory
     *     subcategory object to be retrieved.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public Subcategory searchSubcategoryByName(String name) throws ApplicationException;
    
    /**
     * Updates the subcategory object into the database.
     * @param subcategory
     *     subcategory object to be updated.
     * @return True
     *     If subcategory object is updated.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */
    public boolean updateSubcategory(Subcategory subcategory) throws ApplicationException;
    
    /**
     * Deletes entire subcategory object from the database.
     * @param subcategory
     *     subcategory object to be deleted.
     * @return True 
     *     If subcategory object is deleted.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
     */  
    public boolean deleteSubcategory(Subcategory subcategory) throws ApplicationException;

}