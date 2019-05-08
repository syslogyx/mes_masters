package com.syslogyx.dao.master;

import java.util.List;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.model.masters.CodeGroupDO;

public interface IMasterDAO {

	List<CodeGroupDO> getCodeGroupList(RequestBO requestFilter, int page, int limit);

	long getCodeGroupListSize(RequestBO requestFilter);

}
