package com.syslogyx.dao.base;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syslogyx.constants.IConstants;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;

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
				Field declaredField = classT.getDeclaredField("status");
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
	public Object getEntityById(Class<?> classT, Object entity_id) {
		return entityManager.find(classT, entity_id);
	}

}
