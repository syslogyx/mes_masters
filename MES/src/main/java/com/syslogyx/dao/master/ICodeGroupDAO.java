package com.syslogyx.dao.master;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.syslogyx.model.masters.CodeGroupDO;

/**
 * This Interface is used to communicating with db
 * 
 * @author palash
 *
 */
public interface ICodeGroupDAO extends CrudRepository<CodeGroupDO, Integer> {

	/**
	 * Fetch the Code Group Object by Group code
	 * 
	 * @param group_code
	 *            : Holds group code value to filter from table
	 * @return
	 */
	@Query("select cg from CodeGroupDO cg where cg.group_code=?1")
	CodeGroupDO findByGroupCode(String group_code);

	/**
	 * for fetch the CodeGroup by id
	 * 
	 * @param code_group_id
	 * @return
	 */
	CodeGroupDO findById(int code_group_id);

}
