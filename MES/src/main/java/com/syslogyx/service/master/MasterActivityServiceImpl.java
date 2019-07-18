package com.syslogyx.service.master;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.constants.IConstants;
import com.syslogyx.dao.master.IMasterActivityDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.service.BaseService;

/**
 * This class is used for implementing business logic related to MasterActivity
 * Module
 * 
 * @author palash
 *
 */
@Service
@Transactional(rollbackFor = { ApplicationException.class, Exception.class })
public class MasterActivityServiceImpl extends BaseService implements IMasterActivityService {

	@Autowired
	private IMasterActivityDAO imasterActivityDAO;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Object getMasterById(int master_id, int page, int limit) throws ApplicationException {

		List<?> mastersActivitylogList = imasterActivityDAO.getMastersActivitylogList((master_id), page, limit,
				IResponseMessages.INVALID_MASTER_ID);
		if (mastersActivitylogList != null && !mastersActivitylogList.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = imasterActivityDAO.getMastersSize(master_id);

				return generatePaginationResponse(mastersActivitylogList, listSize, page, limit);
			}
			return mastersActivitylogList;
		}
		return null;

	}

}
