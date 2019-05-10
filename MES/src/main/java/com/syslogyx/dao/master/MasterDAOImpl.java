package com.syslogyx.dao.master;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.user.UserDO;

/**
 * This class is used to fetch data from CodeGroup Table and Perform all db
 * related operation
 * 
 * @author namrata
 *
 */
@Repository
@Transactional(rollbackOn = { ApplicationException.class, Exception.class })
public class MasterDAOImpl implements IMasterDAO {

	@Autowired
	private EntityManager entityManager;

	/**
	 * This method is used for pagination 
	 */
	@Override
	public List<CodeGroupDO> getCodeGroupList(RequestBO requestFilter, int page, int limit) {

		Query query = entityManager.createQuery(getCriteriaWithFilter(requestFilter));

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

	/**
	 * Prepare the Criteria for Code Groups by adding required filters
	 * 
	 * @param requestFilter
	 * @return
	 */
	private CriteriaQuery<CodeGroupDO> getCriteriaWithFilter(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CodeGroupDO> createQuery = builder.createQuery(CodeGroupDO.class);
		Root<CodeGroupDO> codeGroupRoot = createQuery.from(CodeGroupDO.class);
		Join<CodeGroupDO, UserDO> fetch = codeGroupRoot.join("created_by");

		if (requestFilter != null && requestFilter.getQuick_finder() != null
				&& !requestFilter.getQuick_finder().isEmpty()) {

			createQuery.where(builder.or(
					builder.like(codeGroupRoot.get("group_code"), "%" + requestFilter.getQuick_finder() + "%"),
					builder.like(codeGroupRoot.get("group_desc"), "%" + requestFilter.getQuick_finder() + "%")));
		}

		CompoundSelection<CodeGroupDO> construct = builder.construct(CodeGroupDO.class, codeGroupRoot.get("id"),
				codeGroupRoot.get("group_code"), codeGroupRoot.get("group_desc"), fetch.get("username"),
				codeGroupRoot.get("created"), codeGroupRoot.get("updated"), codeGroupRoot.get("status"));

		return createQuery.select(construct);
	}

	/**
	 * 
	 * This method is used for count all Numbers of rows in CodeGroup table from db
	 */

	@Override
	public long getCodeGroupListSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<CodeGroupDO> codeGroupRoot = createQuery.from(CodeGroupDO.class);

		createQuery.select(builder.count(codeGroupRoot));

		if (requestFilter != null && requestFilter.getQuick_finder() != null
				&& !requestFilter.getQuick_finder().isEmpty()) {

			createQuery.where(builder.or(
					builder.like(codeGroupRoot.get("group_code"), "%" + requestFilter.getQuick_finder() + "%"),
					builder.like(codeGroupRoot.get("group_desc"), "%" + requestFilter.getQuick_finder() + "%")));
		}

		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();
	}
}
