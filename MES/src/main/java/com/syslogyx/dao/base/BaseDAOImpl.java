package com.syslogyx.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	public Object validateEntityById(Class<?> classT, Object entity_id, String error_msg) throws ApplicationException {
		if (entity_id != null && ((entity_id instanceof Integer && (int) entity_id > IConstants.VALUE_ZERO)
				|| (entity_id instanceof Long && (long) entity_id > IConstants.VALUE_ZERO))) {

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Object> createQuery = builder.createQuery(Object.class);
			Root<?> entityRoot = createQuery.from(classT);

			createQuery.where(builder.notEqual(entityRoot.get("status"), IConstants.STATUS_INACTIVE),
					builder.equal(entityRoot.get("id"), entity_id));

			Query query = entityManager.createQuery(createQuery);
			Object entity = query.getSingleResult();

			if (entity == null)
				throw new ApplicationException(IResponseCodes.INVALID_ENTITY, error_msg);

			return entity;
		}
		return null;
	}

}
