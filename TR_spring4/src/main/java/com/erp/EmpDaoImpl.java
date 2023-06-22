package com.erp;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;

import com.vo.DeptVO;
import com.vo.EmpVO;

public class EmpDaoImpl implements EmpMapper{
	Logger logger =   
			Logger.getLogger(EmpDaoImpl.class);
	public SqlSessionTemplate sqlSessionTemplate = null;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public int empInsert(EmpVO peVO) throws DataAccessException {
		logger.info("empInsert 호출 성공");
		int result =  
			sqlSessionTemplate.insert("empInsert",peVO);
		return result;
	}
}




