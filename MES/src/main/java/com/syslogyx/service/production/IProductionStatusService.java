package com.syslogyx.service.production;

import com.syslogyx.model.masters.MastersDO;

/**
 * This class is used for implementing business logic related to
 * ProductionStatus Module
 * 
 * @author palash
 *
 */
public interface IProductionStatusService {

	/**
	 * For Storing the Production Status Data in db
	 * 
	 * @param mastersDO
	 */
	void createProductionStatus(MastersDO mastersDO);

}
