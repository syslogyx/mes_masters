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
import com.syslogyx.dao.base.BaseDAOImpl;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.ProcessUnitDO;
import com.syslogyx.model.masters.DPRTargetDO;
import com.syslogyx.model.masters.ProductDefDO;
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
		Join<CodeGroupDO, UserDO> fetch = codeGroupRoot.join(IPropertyConstant.UPDATED_BY);

		// set the list of properties whose values are required to fetch
		CompoundSelection<CodeGroupDO> construct = builder.construct(CodeGroupDO.class,
				codeGroupRoot.get(IPropertyConstant.ID), codeGroupRoot.get(IPropertyConstant.GROUP_CODE),
				codeGroupRoot.get(IPropertyConstant.GROUP_DESC), fetch.get(IPropertyConstant.USERNAME),
				codeGroupRoot.get(IPropertyConstant.CREATED), codeGroupRoot.get(IPropertyConstant.UPDATED),
				codeGroupRoot.get(IPropertyConstant.STATUS));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addCriteriaForCodeGroupFilter(requestFilter, builder, codeGroupRoot);

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
	private List<Predicate> addCriteriaForCodeGroupFilter(RequestBO requestFilter, CriteriaBuilder builder,
			Root<CodeGroupDO> codeGroupRoot) {

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
		List<Predicate> conditions = addCriteriaForCodeGroupFilter(requestFilter, builder, codeGroupRoot);

		// add the list of predicates in where clause
		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}

		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();
	}

	@Override
	public List findMastersList(String master_name) {

		if (master_name.equals(IConstants.MASTERS_NAME.CODE_GROUP))
			return getCodeGroupList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		if (master_name.equals(IConstants.MASTERS_NAME.CAMPAIGN))
			return getCampaignList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		if (master_name.equals(IConstants.MASTERS_NAME.DPR_TARGET))
			return getDPRTargetList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		return null;
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

		List<Predicate> conditions = getConditionsForCampaign(requestFilter, builder, campaignRoot, processUnitFetch);

		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}

		CompoundSelection<CampaignDO> construct = builder.construct(CampaignDO.class,
				campaignRoot.get(IPropertyConstant.ID), campaignRoot.get(IPropertyConstant.CAMPAIGN_ID),
				campaignRoot.get(IPropertyConstant.ATTRIBUTE), campaignRoot.get(IPropertyConstant.AIM),
				campaignRoot.get(IPropertyConstant.CAPACITY_MIN), campaignRoot.get(IPropertyConstant.CAPACITY_MAX),
				campaignRoot.get(IPropertyConstant.PRIORITY_LEVEL), campaignRoot.get(IPropertyConstant.CREATED),
				campaignRoot.get(IPropertyConstant.UPDATED), campaignRoot.get(IPropertyConstant.STATUS),
				fetch.get(IPropertyConstant.USERNAME), processUnitFetch.get(IPropertyConstant.UNIT),
				processUnitFetch.get(IPropertyConstant.ID));

		return createQuery.select(construct);
	}

	/**
	 * Prepare the list of Predicates according to the provided filter request
	 * 
	 * @param requestFilter
	 * @param builder
	 * @param campaignRoot
	 * @param processUnitFetch
	 * @return
	 */
	private List<Predicate> getConditionsForCampaign(RequestBO requestFilter, CriteriaBuilder builder,
			Root<CampaignDO> campaignRoot, Join<CampaignDO, ProcessUnitDO> processUnitFetch) {
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
			return conditions;
		}
		return null;
	}

	@Override
	public long getCampaignListSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<CampaignDO> campaignRoot = createQuery.from(CampaignDO.class);
		Join<CampaignDO, ProcessUnitDO> processUnitFetch = campaignRoot.join(IPropertyConstant.HOLD_UNIT);

		createQuery.select(builder.count(campaignRoot));

		List<Predicate> conditions = getConditionsForCampaign(requestFilter, builder, campaignRoot, processUnitFetch);

		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}
		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();
	}

	@Override
	public List<DPRTargetDO> getDPRTargetList(RequestBO requestFilter, int page, int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DPRTargetDO> createQuery = builder.createQuery(DPRTargetDO.class);
		Root<DPRTargetDO> dprTargetRoot = createQuery.from(DPRTargetDO.class);
		Join<DPRTargetDO, UserDO> fetch = dprTargetRoot.join(IPropertyConstant.UPDATED_BY);
		Join<DPRTargetDO, ProcessUnitDO> unitJoin = dprTargetRoot.join(IPropertyConstant.UNIT);
		Join<DPRTargetDO, ProductDefDO> productJoin = dprTargetRoot.join(IPropertyConstant.PRODUCT);

		// set the list of properties whose values are required to fetch
		CompoundSelection<DPRTargetDO> construct = builder.construct(DPRTargetDO.class,
				dprTargetRoot.get(IPropertyConstant.ID), dprTargetRoot.get(IPropertyConstant.BUSINESS_PLAN_TARGET),
				dprTargetRoot.get(IPropertyConstant.INTERNAL_TARGET), dprTargetRoot.get(IPropertyConstant.YEAR),
				dprTargetRoot.get(IPropertyConstant.UPDATED), dprTargetRoot.get(IPropertyConstant.STATUS),
				unitJoin.get(IPropertyConstant.ID), productJoin.get(IPropertyConstant.ID),
				productJoin.get(IPropertyConstant.PRODUCT_FORM), unitJoin.get(IPropertyConstant.UNIT),
				fetch.get(IPropertyConstant.USERNAME));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addCriteriaForDPRFilter(requestFilter, builder, dprTargetRoot);

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

	@Override
	public long getDPRTargetListSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<DPRTargetDO> dprTargetRoot = createQuery.from(DPRTargetDO.class);

		createQuery.select(builder.count(dprTargetRoot));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addCriteriaForDPRFilter(requestFilter, builder, dprTargetRoot);

		// add the list of predicates in where clause
		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}

		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();
	}

	/**
	 * Prepare list of conditions to add in where clause according to the provided
	 * filter
	 * 
	 * @param requestFilter
	 * @param builder
	 * @param dprTargetRoot
	 * @return
	 */
	private List<Predicate> addCriteriaForDPRFilter(RequestBO requestFilter, CriteriaBuilder builder,
			Root<DPRTargetDO> dprTargetRoot) {
		if (requestFilter != null) {

			Join<DPRTargetDO, ProcessUnitDO> unitJoin = dprTargetRoot.join(IPropertyConstant.UNIT);
			Join<DPRTargetDO, ProductDefDO> productJoin = dprTargetRoot.join(IPropertyConstant.PRODUCT);
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(dprTargetRoot.get(IPropertyConstant.YEAR),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(unitJoin.get(IPropertyConstant.UNIT), "%" + requestFilter.getQuick_finder() + "%"),
						builder.like(productJoin.get(IPropertyConstant.PRODUCT_FORM),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions.add(builder.notEqual(dprTargetRoot.get("status"), IConstants.STATUS_INACTIVE));
			}
			return conditions;
		}
		return null;
	}

}
