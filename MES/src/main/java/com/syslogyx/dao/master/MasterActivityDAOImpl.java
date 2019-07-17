package com.syslogyx.dao.master;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.constants.IPropertyConstant;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.activitylog.CodeGroupDOActivityLog;
import com.syslogyx.model.user.UserDO;

/**
 * This class is used to fetch data from CodeGroupActivityLog table in db
 * 
 * @author Palash
 *
 */
@Repository
@Transactional(rollbackOn = { ApplicationException.class, Exception.class })
public class MasterActivityDAOImpl implements IMasterActivityDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CodeGroupDOActivityLog> getCodeGroupActivityList(RequestBO requestFilter, int page, int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CodeGroupDOActivityLog> createQuery = builder.createQuery(CodeGroupDOActivityLog.class);
		Root<CodeGroupDOActivityLog> codeGroupActivityRoot = createQuery.from(CodeGroupDOActivityLog.class);

		Object[] queryResults = getConditionsForCodeGroupActivityLog(requestFilter, builder, codeGroupActivityRoot,
				true);

		if (queryResults != null && queryResults.length > IConstants.VALUE_ZERO) {

			List<Predicate> conditions = (List<Predicate>) queryResults[0];

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}

			Query query = entityManager
					.createQuery(createQuery.select((Selection<? extends CodeGroupDOActivityLog>) queryResults[1]));

			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				int start_index = IConstants.VALUE_ZERO;
				if (page > 1) {
					page -= 1;
					start_index = page * limit;
				}

				query.setFirstResult(start_index);
				query.setMaxResults(limit);
			}

			return query.getResultList();
		}
		return null;
	}

	private Object[] getConditionsForCodeGroupActivityLog(RequestBO requestFilter, CriteriaBuilder builder,
			Root<CodeGroupDOActivityLog> codeGroupActivityRoot, boolean prepareConstructor) {

		Object[] resultSet = new Object[2];

		Join<CodeGroupDOActivityLog, UserDO> fetch = codeGroupActivityRoot.join(IPropertyConstant.UPDATED_BY,
				JoinType.LEFT);

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(codeGroupActivityRoot.get(IPropertyConstant.GROUP_CODE),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(codeGroupActivityRoot.get(IPropertyConstant.GROUP_DESC),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(codeGroupActivityRoot.get(IPropertyConstant.INCREMENTOR),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions.add(builder.notEqual(codeGroupActivityRoot.get(IPropertyConstant.STATUS),
						IConstants.STATUS_INACTIVE));
			}

			resultSet[0] = conditions;
		}

		// add construct in case if the identifier is true to fetch the limited details
		// from list
		if (prepareConstructor) {
			CompoundSelection<CodeGroupDOActivityLog> construct = builder.construct(CodeGroupDOActivityLog.class,
					codeGroupActivityRoot.get(IPropertyConstant.ID),
					codeGroupActivityRoot.get(IPropertyConstant.GROUP_CODE),
					codeGroupActivityRoot.get(IPropertyConstant.GROUP_DESC),
					codeGroupActivityRoot.get(IPropertyConstant.INCREMENTOR),
					codeGroupActivityRoot.get(IPropertyConstant.CREATED),
					codeGroupActivityRoot.get(IPropertyConstant.UPDATED),
					codeGroupActivityRoot.get(IPropertyConstant.STATUS), fetch.get(IPropertyConstant.USERNAME));
			resultSet[1] = construct;
		}
		return resultSet;

	}

	@Override
	public long getCodeGroupActivityListSize(RequestBO requestFilter) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<CodeGroupDOActivityLog> codeGroupActivityRoot = createQuery.from(CodeGroupDOActivityLog.class);

		createQuery.select(builder.count(codeGroupActivityRoot));

		Object[] queryResults = getConditionsForCodeGroupActivityLog(requestFilter, builder, codeGroupActivityRoot,
				false);

		if (queryResults != null && queryResults.length > IConstants.VALUE_ZERO) {
			@SuppressWarnings("unchecked")
			List<Predicate> conditions = (List<Predicate>) queryResults[0];

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}

			Query query = entityManager.createQuery(createQuery);
			return (long) query.getSingleResult();
		}
		return IConstants.VALUE_ZERO;
	}

}
