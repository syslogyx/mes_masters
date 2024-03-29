package com.syslogyx.dao.base;

import com.syslogyx.exception.ApplicationException;

/**
 * The DAO interface for declaring the common methods which needs to access from
 * every DAO layer
 * 
 * @author namrata
 *
 */
public interface IBaseDAO {

	/**
	 * Validate the Entity is present in DB and is in Active state or not, in case
	 * it is inactive throw exception with provided error message
	 * 
	 * @param classT
	 *            : Name of the Entity class
	 * @param entity_id
	 *            : Primary key Unique id of the Entity
	 * @param error_msg
	 *            : Error msg to show in case of invalid id
	 * @return
	 * @throws ApplicationException
	 * @throws Exception
	 */
	Object validateEntityById(Class<?> classT, Object entity_id, String error_msg)
			throws ApplicationException, Exception;

	/**
	 * Fetch the Entity according to provided class name, property name and it's
	 * value
	 * 
	 * @param classT
	 *            : Name of the Entity class
	 * @param property_name
	 *            : Name of the property by which we need to search
	 * @param property_value
	 *            : Required value of the property
	 * @return
	 * @throws ApplicationException
	 * @throws Exception
	 */
	Object getEntityByPropertyName(Class<?> classT, String property_name, Object property_value);

	/**
	 * Get the Entity by Id
	 * 
	 * @param classT
	 *            : Name of the Entity class
	 * @param entity_id
	 *            : Primary key Unique id of the Entity
	 * @throws ApplicationException 
	 */
	Object getEntityById(Class<?> classT, Object entity_id) throws ApplicationException;

	/**
	 * Merge the Provided Entity in DB
	 * 
	 * @param master
	 */
	void mergeEntity(Object entity);

	
	
	
	
	
	
	
	
}
