package com.syslogyx.dao.master;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syslogyx.constants.IConstants;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.MastersDO;
import com.syslogyx.model.masters.activitylog.CodeGroupDOActivityLog;

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
	public long getMastersSize(int master_id) {
		return master_id;
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Long> createQuery = builder.createQuery(Long.class);
//		Root<?> mastersRoot = createQuery.from(getMasterName(master_id));
//		
//		createQuery.select(builder.count(mastersRoot));
//		
//		Query query = entityManager.createQuery(createQuery);
//		return (long) query.getSingleResult();
//
	}

	@Override
	public List<?> getMastersActivitylogList(int master_id, int page, int limit, String invalidMasterId) {

		if (master_id == 2) {
			Query createQuery = entityManager.createQuery("SELECT ca FROM CodeGroupDOActivityLog ca");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 3) {
			Query createQuery = entityManager.createQuery("SELECT ca FROM CampaignDOActivityLog ca");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 4) {
			Query createQuery = entityManager.createQuery("SELECT ca FROM DPRTargetDOActivityLog ca");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 5) {
			Query createQuery = entityManager.createQuery("SELECT el FROM ElongationDOActivityLog el");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 6) {
			Query createQuery = entityManager.createQuery("SELECT lt FROM LeadTimeDOActivityLog lt");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 7) {
			Query createQuery = entityManager.createQuery("SELECT pf FROM ProcessFamilyDOActivityLog pf");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 8) {
			Query createQuery = entityManager.createQuery("SELECT pu FROM ProcessUnitDOActivityLog pu");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 9) {
			Query createQuery = entityManager.createQuery("SELECT pd FROM ProductDefDOActivityLog pd");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 10) {
			Query createQuery = entityManager.createQuery("SELECT sl FROM ShelfLifeDOActivityLog sl");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 11) {
			Query createQuery = entityManager.createQuery("SELECT sa FROM ShrinkageDOActivityLog sa");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 12) {
			Query createQuery = entityManager.createQuery("SELECT tm FROM TrimmingDOActivityLog tm");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		} else if (master_id == 13) {
			Query createQuery = entityManager.createQuery("SELECT tn FROM ThicknessDOActivityLog tn");
			Query paginationQuery = getPagination(page, limit, createQuery);
			return paginationQuery.getResultList();
		}

		return null;
	}

	/**
	 * For get Pagination
	 * 
	 * @param page
	 * @param limit
	 * @param query
	 * @return
	 */
	private Query getPagination(int page, int limit, Query query) {

		int start_index = IConstants.VALUE_ZERO;
		if (page > 1) {
			page -= 1;
			start_index = page * limit;
		}

		query.setFirstResult(start_index);
		query.setMaxResults(limit);
		return query;

	}

	// return master Activity name
	private Class<?> getMasterName(int master_id) {

		if (master_id == 2)
			return CodeGroupDOActivityLog.class;

		return null;
	}

}
