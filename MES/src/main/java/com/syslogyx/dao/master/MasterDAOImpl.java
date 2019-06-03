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
import com.syslogyx.dao.base.BaseDAOImpl;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.masters.CRGradeDO;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.DPRTargetDO;
import com.syslogyx.model.masters.ElongationDO;
import com.syslogyx.model.masters.LeadTimeDO;
import com.syslogyx.model.masters.ProcessFamilyDO;
import com.syslogyx.model.masters.ProcessTypeDO;
import com.syslogyx.model.masters.ProcessUnitDO;
import com.syslogyx.model.masters.ProductDefDO;
import com.syslogyx.model.masters.ProductFormDO;
import com.syslogyx.model.masters.ProductTypeDO;
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
		createQuery.select(codeGroupRoot).orderBy(builder.asc(codeGroupRoot.get(IPropertyConstant.GROUP_DESC)));
		// createQuery.select(codeGroupRoot).orderBy(builder.desc(codeGroupRoot.get("group_code")));

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
	public List findMastersList(String master_name) throws ApplicationException {

		if (master_name.equals(IConstants.MASTERS_NAME.CODE_GROUP))
			return getCodeGroupList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		else if (master_name.equals(IConstants.MASTERS_NAME.CAMPAIGN))
			return getCampaignList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		else if (master_name.equals(IConstants.MASTERS_NAME.DPR_TARGET))
			return getDPRTargetList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		else if (master_name.equals(IConstants.MASTERS_NAME.LEAD_TIME))
			return getLeadTimeList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		else if (master_name.equals(IConstants.MASTERS_NAME.ELONGATION))
			return getElongationList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_FAMILY))
			return getProcessFamilyList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_UNIT))
			return getProcessUnitList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		else if (master_name.equals(IConstants.MASTERS_NAME.PRODUCT))
			return getProductList(null, IConstants.DEFAULT, IConstants.DEFAULT);

		throw new ApplicationException(IResponseCodes.SERVER_ERROR, IResponseMessages.INVALID_MASTER_NAME);
	}

	@Override
	public List<CampaignDO> getCampaignList(RequestBO requestFilter, int page, int limit) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CampaignDO> createQuery = builder.createQuery(CampaignDO.class);
		Root<CampaignDO> campaignRoot = createQuery.from(CampaignDO.class);

		Object[] queryResults = getConditionsForCampaign(requestFilter, builder, campaignRoot, true);

		if (queryResults != null && queryResults.length > IConstants.VALUE_ZERO) {

			List<Predicate> conditions = (List<Predicate>) queryResults[0];

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}

			Query query = entityManager
					.createQuery(createQuery.select((Selection<? extends CampaignDO>) queryResults[1]));

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

	/**
	 * Prepare the list of Predicates according to the provided filter request
	 * 
	 * @param requestFilter
	 * @param builder
	 * @param campaignRoot
	 * @return
	 */
	private Object[] getConditionsForCampaign(RequestBO requestFilter, CriteriaBuilder builder,
			Root<CampaignDO> campaignRoot, boolean prepareContruct) {

		Object[] resultSet = new Object[2];

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

			resultSet[0] = conditions;
		}

		// add construct in case if the identifier is true to fetch the limited details
		// from list
		if (prepareContruct) {
			CompoundSelection<CampaignDO> construct = builder.construct(CampaignDO.class,
					campaignRoot.get(IPropertyConstant.ID), campaignRoot.get(IPropertyConstant.CAMPAIGN_ID),
					campaignRoot.get(IPropertyConstant.ATTRIBUTE), campaignRoot.get(IPropertyConstant.AIM),
					campaignRoot.get(IPropertyConstant.CAPACITY_MIN), campaignRoot.get(IPropertyConstant.CAPACITY_MAX),
					campaignRoot.get(IPropertyConstant.PRIORITY_LEVEL), campaignRoot.get(IPropertyConstant.CREATED),
					campaignRoot.get(IPropertyConstant.UPDATED), campaignRoot.get(IPropertyConstant.STATUS),
					fetch.get(IPropertyConstant.USERNAME), processUnitFetch.get(IPropertyConstant.UNIT),
					processUnitFetch.get(IPropertyConstant.ID));
			resultSet[1] = construct;
		}
		return resultSet;
	}

	@Override
	public long getCampaignListSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<CampaignDO> campaignRoot = createQuery.from(CampaignDO.class);

		createQuery.select(builder.count(campaignRoot));

		Object[] queryResults = getConditionsForCampaign(requestFilter, builder, campaignRoot, false);

		if (queryResults != null && queryResults.length > IConstants.VALUE_ZERO) {
			List<Predicate> conditions = (List<Predicate>) queryResults[0];

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}

			Query query = entityManager.createQuery(createQuery);
			return (long) query.getSingleResult();
		}
		return IConstants.VALUE_ZERO;
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
				productJoin.get(IPropertyConstant.PRODUCT), unitJoin.get(IPropertyConstant.UNIT),
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

	@Override
	public List<LeadTimeDO> getLeadTimeList(RequestBO requestFilter, int page, int limit) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LeadTimeDO> createQuery = builder.createQuery(LeadTimeDO.class);
		Root<LeadTimeDO> leadTimeRoot = createQuery.from(LeadTimeDO.class);
		Join<LeadTimeDO, UserDO> fetch = leadTimeRoot.join(IPropertyConstant.UPDATED_BY);
		Join<LeadTimeDO, ProcessUnitDO> afterUnitFetch = leadTimeRoot.join(IPropertyConstant.AFTER_PROCESS_UNIT,
				JoinType.LEFT);
		Join<LeadTimeDO, ProcessUnitDO> beforeUnitFetch = leadTimeRoot.join(IPropertyConstant.BEFORE_PROCESS_UNIT,
				JoinType.LEFT);

		createQuery.select(leadTimeRoot).orderBy(builder.asc(leadTimeRoot.get(IPropertyConstant.ID)));

		// createQuery.select(leadTimeRoot).orderBy(builder.asc(afterUnitFetch.get("unit")),
		// builder.desc(beforeUnitFetch.get("unit")));

		CompoundSelection<LeadTimeDO> construct = builder.construct(LeadTimeDO.class,
				leadTimeRoot.get(IPropertyConstant.ID), leadTimeRoot.get(IPropertyConstant.IDEAL_TIME_MIN),
				leadTimeRoot.get(IPropertyConstant.IDEAL_TIME_MAX), leadTimeRoot.get(IPropertyConstant.HANDLE_TIME_MIN),
				leadTimeRoot.get(IPropertyConstant.HANDLE_TIME_MAX), beforeUnitFetch.get(IPropertyConstant.ID),
				beforeUnitFetch.get(IPropertyConstant.UNIT), afterUnitFetch.get(IPropertyConstant.ID),
				afterUnitFetch.get(IPropertyConstant.UNIT), fetch.get(IPropertyConstant.USERNAME),
				leadTimeRoot.get(IPropertyConstant.UPDATED), leadTimeRoot.get(IPropertyConstant.STATUS));

		List<Predicate> conditions = getConditionsForLeadTime(requestFilter, builder, leadTimeRoot, afterUnitFetch,
				beforeUnitFetch);

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
	 * Prepare list of conditions to add in where clause according to the provided
	 * filter
	 * 
	 * @param requestFilter
	 * @param builder
	 * @param leadTimeRoot
	 * @return
	 */
	private List<Predicate> getConditionsForLeadTime(RequestBO requestFilter, CriteriaBuilder builder,
			Root<LeadTimeDO> leadTimeRoot, Join<LeadTimeDO, ProcessUnitDO> afterUnitFetch,
			Join<LeadTimeDO, ProcessUnitDO> beforeUnitFetch) {

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(afterUnitFetch.get(IPropertyConstant.UNIT),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(beforeUnitFetch.get(IPropertyConstant.UNIT),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(leadTimeRoot.get(IPropertyConstant.IDEAL_TIME_MIN),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(leadTimeRoot.get(IPropertyConstant.IDEAL_TIME_MAX),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(leadTimeRoot.get(IPropertyConstant.HANDLE_TIME_MIN),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(leadTimeRoot.get(IPropertyConstant.HANDLE_TIME_MAX),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions
						.add(builder.notEqual(leadTimeRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}
			return conditions;
		}
		return null;

	}

	@Override
	public long getLeadTimeListSize(RequestBO requestFilter) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<LeadTimeDO> leadTimeRoot = createQuery.from(LeadTimeDO.class);
		Join<LeadTimeDO, ProcessUnitDO> afterUnitFetch = leadTimeRoot.join(IPropertyConstant.AFTER_PROCESS_UNIT,
				JoinType.LEFT);
		Join<LeadTimeDO, ProcessUnitDO> beforeUnitFetch = leadTimeRoot.join(IPropertyConstant.BEFORE_PROCESS_UNIT,
				JoinType.LEFT);

		createQuery.select(builder.count(leadTimeRoot));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = getConditionsForLeadTime(requestFilter, builder, leadTimeRoot, afterUnitFetch,
				beforeUnitFetch);

		// add the list of predicates in where clause
		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}

		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();

	}

	@Override
	public List<ElongationDO> getElongationList(RequestBO requestFilter, int page, int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ElongationDO> createQuery = builder.createQuery(ElongationDO.class);
		Root<ElongationDO> elongationRoot = createQuery.from(ElongationDO.class);
		Join<ElongationDO, UserDO> updatedBy = elongationRoot.join(IPropertyConstant.UPDATED_BY);
		Join<ElongationDO, ProcessUnitDO> unit = elongationRoot.join(IPropertyConstant.UNIT, JoinType.LEFT);
		Join<ElongationDO, CRGradeDO> crGrade = elongationRoot.join(IPropertyConstant.CR_GRADE, JoinType.LEFT);

		// set the list of properties whose values are required to fetch
		CompoundSelection<ElongationDO> construct = builder.construct(ElongationDO.class,
				elongationRoot.get(IPropertyConstant.ID), unit.get(IPropertyConstant.ID),
				unit.get(IPropertyConstant.UNIT), crGrade.get(IPropertyConstant.ID),
				crGrade.get(IPropertyConstant.NAME), elongationRoot.get(IPropertyConstant.UPDATED),
				elongationRoot.get(IPropertyConstant.STATUS), updatedBy.get(IPropertyConstant.USERNAME));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addCriteriaForElongationFilter(requestFilter, builder, elongationRoot, unit,
				crGrade);

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
	 * Prepare the List of Conditions based on the filters provided in the request
	 * body
	 * 
	 * @param requestFilter
	 * @param builder
	 * @param elongationRoot
	 * @param unit
	 * @param crGrade
	 * @return
	 */
	private List<Predicate> addCriteriaForElongationFilter(RequestBO requestFilter, CriteriaBuilder builder,
			Root<ElongationDO> elongationRoot, Join<ElongationDO, ProcessUnitDO> unit,
			Join<ElongationDO, CRGradeDO> crGrade) {
		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(unit.get(IPropertyConstant.UNIT), "%" + requestFilter.getQuick_finder() + "%"),
						builder.like(crGrade.get(IPropertyConstant.NAME),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions.add(
						builder.notEqual(elongationRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}
			return conditions;
		}
		return null;
	}

	@Override
	public long getElongationListSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<ElongationDO> elongationRoot = createQuery.from(ElongationDO.class);
		Join<ElongationDO, ProcessUnitDO> unit = elongationRoot.join(IPropertyConstant.UNIT, JoinType.LEFT);
		Join<ElongationDO, CRGradeDO> crGrade = elongationRoot.join(IPropertyConstant.CR_GRADE, JoinType.LEFT);

		createQuery.select(builder.count(elongationRoot));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addCriteriaForElongationFilter(requestFilter, builder, elongationRoot, unit,
				crGrade);

		// add the list of predicates in where clause
		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}

		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();
	}

	@Override
	public List<ProcessFamilyDO> getProcessFamilyList(RequestBO requestFilter, int page, int limit) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProcessFamilyDO> createQuery = builder.createQuery(ProcessFamilyDO.class);
		Root<ProcessFamilyDO> processFamilyRoot = createQuery.from(ProcessFamilyDO.class);
		Join<ProcessFamilyDO, UserDO> fetch = processFamilyRoot.join(IPropertyConstant.UPDATED_BY);
		Join<ProcessFamilyDO, ProcessTypeDO> processTypeFetch = processFamilyRoot.join(IPropertyConstant.PROCESS_TYPE,
				JoinType.LEFT);

		// set the list of properties whose values are required to fetch
		CompoundSelection<ProcessFamilyDO> construct = builder.construct(ProcessFamilyDO.class,
				processFamilyRoot.get(IPropertyConstant.ID), processFamilyRoot.get(IPropertyConstant.PROCESS_FAMILY),
				processFamilyRoot.get(IPropertyConstant.PRIORITY), processFamilyRoot.get(IPropertyConstant.BUCKET),
				processFamilyRoot.get(IPropertyConstant.UPDATED), processFamilyRoot.get(IPropertyConstant.STATUS),
				processTypeFetch.get(IPropertyConstant.ID), processTypeFetch.get(IPropertyConstant.NAME),
				fetch.get(IPropertyConstant.USERNAME));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addConditionsForProcessFamily(requestFilter, builder, processFamilyRoot,
				processTypeFetch);

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
	 * Prepare the List of Conditions based on the filters provided in the request
	 * body
	 * 
	 * @param requestFilter
	 * @param builder
	 * @param processFamilyRoot
	 * @param processTypeFetch
	 * @return
	 */
	private List<Predicate> addConditionsForProcessFamily(RequestBO requestFilter, CriteriaBuilder builder,
			Root<ProcessFamilyDO> processFamilyRoot, Join<ProcessFamilyDO, ProcessTypeDO> processTypeFetch) {

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(processFamilyRoot.get(IPropertyConstant.PROCESS_FAMILY),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processTypeFetch.get(IPropertyConstant.NAME),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processFamilyRoot.get(IPropertyConstant.BUCKET),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions.add(
						builder.notEqual(processFamilyRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}
			return conditions;
		}
		return null;

	}

	@Override
	public long getProcessFamilyListSize(RequestBO requestFilter) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<ProcessFamilyDO> processFamilyRoot = createQuery.from(ProcessFamilyDO.class);
		Join<ProcessFamilyDO, ProcessTypeDO> processTypeFetch = processFamilyRoot.join(IPropertyConstant.PROCESS_TYPE,
				JoinType.LEFT);

		createQuery.select(builder.count(processFamilyRoot));

		// prepare where conditions according to provided filter
		List<Predicate> conditions = addConditionsForProcessFamily(requestFilter, builder, processFamilyRoot,
				processTypeFetch);

		// add the list of predicates in where clause
		if (conditions != null && !conditions.isEmpty()) {
			createQuery.where(conditions.toArray(new Predicate[] {}));
		}

		Query query = entityManager.createQuery(createQuery);
		return (long) query.getSingleResult();

	}

	@Override
	public List<ProcessUnitDO> getProcessUnitList(RequestBO requestFilter, int page, int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProcessUnitDO> createQuery = builder.createQuery(ProcessUnitDO.class);
		Root<ProcessUnitDO> processUnitRoot = createQuery.from(ProcessUnitDO.class);

		Object[] queryResults = getConditionsForProcessUnit(requestFilter, builder, processUnitRoot, true);

		if (queryResults != null && queryResults.length > IConstants.VALUE_ZERO) {

			List<Predicate> conditions = (List<Predicate>) queryResults[0];

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}

			Query query = entityManager
					.createQuery(createQuery.select((Selection<? extends ProcessUnitDO>) queryResults[1]));

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

	/**
	 * Add filters to the Process Unit and Prepare the construct with list of fields
	 * required in the response
	 * 
	 * @param requestFilter
	 * @param builder
	 * @param processUnitRoot
	 * @param prepareContruct
	 * @return
	 */
	private Object[] getConditionsForProcessUnit(RequestBO requestFilter, CriteriaBuilder builder,
			Root<ProcessUnitDO> processUnitRoot, boolean prepareContruct) {
		Object[] resultSet = new Object[2];

		Join<CampaignDO, UserDO> updatedBy = processUnitRoot.join(IPropertyConstant.UPDATED_BY);
		Join<CampaignDO, ProcessFamilyDO> processFamily = processUnitRoot.join(IPropertyConstant.PROCESS_FAMILY);

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(processUnitRoot.get(IPropertyConstant.UNIT),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processUnitRoot.get(IPropertyConstant.COST_CENTER),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processUnitRoot.get(IPropertyConstant.CAPACITY),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processUnitRoot.get(IPropertyConstant.CONST_SETUP_TIME),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processUnitRoot.get(IPropertyConstant.YIELD),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(processFamily.get(IPropertyConstant.PROCESS_FAMILY),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions.add(
						builder.notEqual(processUnitRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}

			resultSet[0] = conditions;
		}

		// add construct in case if the identifier is true to fetch the limited details
		// from list
		if (prepareContruct) {
			CompoundSelection<ProcessUnitDO> construct = builder.construct(ProcessUnitDO.class,
					processUnitRoot.get(IPropertyConstant.ID), processUnitRoot.get(IPropertyConstant.UNIT),
					processUnitRoot.get(IPropertyConstant.COST_CENTER), processUnitRoot.get(IPropertyConstant.CAPACITY),
					processUnitRoot.get(IPropertyConstant.CONST_SETUP_TIME),
					processUnitRoot.get(IPropertyConstant.YIELD), processUnitRoot.get(IPropertyConstant.OSP_IDENTIFIER),
					processUnitRoot.get(IPropertyConstant.UPDATED), processUnitRoot.get(IPropertyConstant.STATUS),
					updatedBy.get(IPropertyConstant.USERNAME), processFamily.get(IPropertyConstant.ID),
					processFamily.get(IPropertyConstant.PROCESS_FAMILY));
			resultSet[1] = construct;
		}
		return resultSet;
	}

	@Override
	public long getProcessUnitListSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<ProcessUnitDO> processUnitRoot = createQuery.from(ProcessUnitDO.class);

		createQuery.select(builder.count(processUnitRoot));

		Object[] queryResults = getConditionsForProcessUnit(requestFilter, builder, processUnitRoot, false);

		if (queryResults != null && queryResults.length > IConstants.VALUE_ZERO) {
			List<Predicate> conditions = (List<Predicate>) queryResults[0];

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}

			Query query = entityManager.createQuery(createQuery);
			return (long) query.getSingleResult();
		}
		return IConstants.VALUE_ZERO;
	}

	@Override
	public List<ProductDefDO> getProductList(RequestBO requestFilter, int page, int limit) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductDefDO> createQuery = builder.createQuery(ProductDefDO.class);
		Root<ProductDefDO> productRoot = createQuery.from(ProductDefDO.class);

		Object[] queryResults = getConditionsForProductDefinition(requestFilter, builder, productRoot, true);

		if (queryResults != null && queryResults.length > IConstants.VALUE_ZERO) {

			List<Predicate> conditions = (List<Predicate>) queryResults[0];

			if (conditions != null && !conditions.isEmpty()) {
				createQuery.where(conditions.toArray(new Predicate[] {}));
			}

			Query query = entityManager
					.createQuery(createQuery.select((Selection<? extends ProductDefDO>) queryResults[1]));

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

	/**
	 * Add filters to the Product Definition and Prepare the construct with list of
	 * fields required in the response
	 * 
	 * @param requestFilter
	 * @param builder
	 * @param productRoot
	 * @param prepareContruct
	 * @return
	 */
	private Object[] getConditionsForProductDefinition(RequestBO requestFilter, CriteriaBuilder builder,
			Root<ProductDefDO> productRoot, boolean prepareContruct) {

		Object[] resultSet = new Object[2];

		Join<ProductDefDO, UserDO> userFetch = productRoot.join(IPropertyConstant.UPDATED_BY);
		Join<ProductDefDO, ProductTypeDO> productTypeFetch = productRoot.join(IPropertyConstant.PRODUCT_TYPE,
				JoinType.LEFT);
		Join<ProductDefDO, ProductFormDO> processFormFetch = productRoot.join(IPropertyConstant.PRODUCT_FORM,
				JoinType.LEFT);

		if (requestFilter != null) {
			List<Predicate> conditions = new ArrayList<>();

			if (requestFilter.getQuick_finder() != null && !requestFilter.getQuick_finder().isEmpty()) {
				conditions.add(builder.or(
						builder.like(productRoot.get(IPropertyConstant.PRODUCT),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(productRoot.get(IPropertyConstant.PROCESS_TYPE),
								"%" + requestFilter.getQuick_finder() + "%"),
						builder.like(productRoot.get(IPropertyConstant.PRODUCT_FORM),
								"%" + requestFilter.getQuick_finder() + "%")));
			}

			// add condition to restrict rows whose status is inactive
			if (!requestFilter.isInclude_inactive_data()) {
				conditions.add(builder.notEqual(productRoot.get(IPropertyConstant.STATUS), IConstants.STATUS_INACTIVE));
			}

			resultSet[0] = conditions;
		}

		// add construct in case if the identifier is true to fetch the limited details
		// from list
		if (prepareContruct) {
			CompoundSelection<ProductDefDO> construct = builder.construct(ProductDefDO.class,
					productRoot.get(IPropertyConstant.ID), productRoot.get(IPropertyConstant.PRODUCT),
					userFetch.get(IPropertyConstant.USERNAME), productRoot.get(IPropertyConstant.UPDATED),
					productRoot.get(IPropertyConstant.STATUS), productTypeFetch.get(IPropertyConstant.ID),
					productTypeFetch.get(IPropertyConstant.NAME), processFormFetch.get(IPropertyConstant.ID),
					processFormFetch.get(IPropertyConstant.NAME));
			resultSet[1] = construct;
		}
		return resultSet;
	}

	@Override
	public long getProductSize(RequestBO requestFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
		Root<ProductDefDO> productRoot = createQuery.from(ProductDefDO.class);

		createQuery.select(builder.count(productRoot));

		Object[] queryResults = getConditionsForProductDefinition(requestFilter, builder, productRoot, false);

		if (queryResults != null && queryResults.length > IConstants.VALUE_ZERO) {
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
