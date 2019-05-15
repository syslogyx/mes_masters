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
import com.syslogyx.constants.IPropertyConstant;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.ProcessUnitDO;
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
		Join<CodeGroupDO, UserDO> fetch = codeGroupRoot.join(IPropertyConstant.CREATED_BY);

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(codeGroupRoot.get(IPropertyConstant.GROUP_CODE),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(codeGroupRoot.get(IPropertyConstant.GROUP_DESC),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions
						.add(builder.notEqual(codeGroupRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}
		}

		CompoundSelection<CodeGroupDO> construct = builder.construct(CodeGroupDO.class,
				codeGroupRoot.get(IPropertyConstant.ID), codeGroupRoot.get(IPropertyConstant.GROUP_CODE),
				codeGroupRoot.get(IPropertyConstant.GROUP_DESC), fetch.get(IPropertyConstant.USERNAME),
				codeGroupRoot.get(IPropertyConstant.CREATED), codeGroupRoot.get(IPropertyConstant.UPDATED),
				codeGroupRoot.get(IPropertyConstant.STATUS));

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

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(codeGroupRoot.get(IPropertyConstant.GROUP_CODE),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(codeGroupRoot.get(IPropertyConstant.GROUP_DESC),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions
						.add(builder.notEqual(codeGroupRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}
		}

		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();
	}

	@Override
	public List<CampaignDO> getCampaignList(RequestBO requestFilter, int page, int limit) {

		Query query = entityManager.createQuery(getCampaignCriteriaWithFilter(requestFilter));

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
	 * for Quick Search and restrict status
	 * 
	 * @param requestFilter
	 * @return
	 */
	private CriteriaQuery<CampaignDO> getCampaignCriteriaWithFilter(RequestBO requestFilter) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CampaignDO> createQuery = builder.createQuery(CampaignDO.class);
		Root<CampaignDO> campaignRoot = createQuery.from(CampaignDO.class);
		Join<CampaignDO, UserDO> fetch = campaignRoot.join(IPropertyConstant.UPDATED_BY);
		Join<CampaignDO, ProcessUnitDO> processUnitFetch = campaignRoot.join(IPropertyConstant.HOLD_UNIT);

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(campaignRoot.get(IPropertyConstant.CAMPAIGN_ID),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(campaignRoot.get(IPropertyConstant.ATTRIBUTE),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(campaignRoot.get(IPropertyConstant.AIM),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processUnitFetch.get(IPropertyConstant.UNIT),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions
						.add(builder.notEqual(campaignRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}
		}

		CompoundSelection<CampaignDO> construct = builder.construct(CampaignDO.class,
				campaignRoot.get(IPropertyConstant.ID), campaignRoot.get(IPropertyConstant.CAMPAIGN_ID),
				campaignRoot.get(IPropertyConstant.ATTRIBUTE), campaignRoot.get(IPropertyConstant.AIM),
				campaignRoot.get(IPropertyConstant.CAPACITY_MIN), campaignRoot.get(IPropertyConstant.CAPACITY_MAX),
				campaignRoot.get(IPropertyConstant.PRIORITY_LEVEL), campaignRoot.get(IPropertyConstant.CREATED),
				campaignRoot.get(IPropertyConstant.UPDATED), campaignRoot.get(IPropertyConstant.STATUS),
				fetch.get(IPropertyConstant.USERNAME), processUnitFetch.get(IPropertyConstant.UNIT),
				processUnitFetch.get(IPropertyConstant.PU_ID));

		return createQuery.select(construct);
	}

	@Override
	public long getCampaignListSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<CampaignDO> campaignRoot = createQuery.from(CampaignDO.class);
		Join<CampaignDO, ProcessUnitDO> processUnitFetch = campaignRoot.join(IPropertyConstant.HOLD_UNIT);

		createQuery.select(builder.count(campaignRoot));

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(campaignRoot.get(IPropertyConstant.CAMPAIGN_ID),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(campaignRoot.get(IPropertyConstant.ATTRIBUTE),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(campaignRoot.get(IPropertyConstant.AIM),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processUnitFetch.get(IPropertyConstant.UNIT),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions
						.add(builder.notEqual(campaignRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}

		}
		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();
	}

}
