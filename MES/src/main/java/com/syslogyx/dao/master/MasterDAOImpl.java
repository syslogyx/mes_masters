package com.syslogyx.dao.master;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.dao.base.BaseDAOImpl;
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
public class MasterDAOImpl extends BaseDAOImpl implements IMasterDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CodeGroupDO> getCodeGroupList(RequestBO requestFilter, int page, int limit) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CodeGroupDO> createQuery = builder.createQuery(CodeGroupDO.class);
		Root<CodeGroupDO> codeGroupRoot = createQuery.from(CodeGroupDO.class);
		Join<CodeGroupDO, UserDO> fetch = codeGroupRoot.join("updated_by");

		// set the list of properties whose values are required to fetch
		CompoundSelection<CodeGroupDO> construct = builder.construct(CodeGroupDO.class, codeGroupRoot.get("id"),
				codeGroupRoot.get("group_code"), codeGroupRoot.get("group_desc"), fetch.get("username"),
				codeGroupRoot.get("created"), codeGroupRoot.get("updated"), codeGroupRoot.get("status"));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addCriteriaForFilter(requestFilter, builder, codeGroupRoot);

		// add the list of predicates in where clause
		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}

		Query query = entityManager.createQuery(createQuery.select(construct));

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
	 * @param codeGroupRoot
	 * @param builder
	 * @return
	 */
	private List<Predicate> addCriteriaForFilter(RequestBO requestFilter, CriteriaBuilder builder,
			Root<CodeGroupDO> codeGroupRoot) {

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(codeGroupRoot.get("group_code"), "%" + requestFilter.getQuick_finder() + "%"),
						builder.like(codeGroupRoot.get("group_desc"), "%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions.add(builder.notEqual(codeGroupRoot.get("status"), IConstants.STATUS_INACTIVE));
			}
			return conditions;
		}
		return null;
	}

	@Override
	public long getCodeGroupListSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<CodeGroupDO> codeGroupRoot = createQuery.from(CodeGroupDO.class);

		createQuery.select(builder.count(codeGroupRoot));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addCriteriaForFilter(requestFilter, builder, codeGroupRoot);

		// add the list of predicates in where clause
		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}

		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();
	}

	@Override
	public List<CodeGroupDO> findMastersList(String master_name) {
		return getCodeGroupList(null, IConstants.DEFAULT, IConstants.DEFAULT);
	}

}
