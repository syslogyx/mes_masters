package com.syslogyx.dao.base;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syslogyx.constants.IConstants;
import com.syslogyx.constants.IPropertyConstant;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;

/**
 * The DAO interface for declaring the common methods which needs to access from
 * every DAO layer
 * 
 * @author namrata
 *
 */
@Repository
public class BaseDAOImpl implements IBaseDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Object validateEntityById(Class<?> classT, Object entity_id, String error_msg)
			throws ApplicationException, Exception {
		if (entity_id != null && ((entity_id instanceof Integer && (int) entity_id > IConstants.VALUE_ZERO)
				|| (entity_id instanceof Long && (long) entity_id > IConstants.VALUE_ZERO))) {

			Object entity = entityManager.find(classT, entity_id);

			if (entity == null)
				throw new ApplicationException(IResponseCodes.INVALID_ENTITY, error_msg);
			else {
				
				// validate the status of the entity, throw exception in case it is inactive
				Field declaredField = classT.getDeclaredField(IPropertyConstant.STATUS);
				int status = (int) declaredField.get(entity);
				
				if (status == IConstants.STATUS_INACTIVE)
					throw new ApplicationException(IResponseCodes.INVALID_ENTITY, error_msg);
			}	
			
			return entity;
		}	
		return null;
	}	
		
	@Override
	public void mergeEntity(Object entity) {
		entityManager.merge(entity);
	}
	
	@Override
	public Object getEntityById(Class<?> classT, Object entity_id) throws ApplicationException {
		Object master_id = entityManager.find(classT, entity_id);
		if(master_id == null)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_MASTER_ID);
		return master_id;
	}

	@Override
	public Object getEntityByPropertyName(Class<?> classT, String property_name, Object property_value) {
		try {
			if (property_name != null && !property_name.isEmpty() && property_value != null) {
				Object result = entityManager
						.createQuery("from " + classT.getName() + " where " + property_name + " = '"
								+ property_value.toString() + "'", classT)
						.setMaxResults(IConstants.VALUE_ONE).getSingleResult();
				return result;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
