package com.syslogyx.dao.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.syslogyx.model.masters.CampaignDO;

/**
 * This interface is used to communicate with db
 * 
 * @author palash
 *
 */
public interface ICampaignDAO extends JpaRepository<CampaignDO, Integer> {

	/**
	 * Fetch the Campaign object by CampaignId
	 * 
	 * @param campaign_id:
	 *            Holds campaign Id value to filter from table
	 * @return
	 */
	@Query("select cp from CampaignDO cp where cp.campaign_id=?1")
	CampaignDO findByCampaignId(String campaign_id);

	/**
	 * For fetch Campaign by Id
	 * 
	 * @param camp_id
	 * @return
	 */
	CampaignDO findById(int camp_id);

}
