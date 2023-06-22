package com.erp;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;

import com.vo.DeptVO;

public class DeptDao{
	Logger logger =   
			Logger.getLogger(DeptDao.class);
	public SqlSessionTemplate sqlSessionTemplate = null;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public int deptInsert(DeptVO pdVO) throws DataAccessException {
		logger.info("deptInsert 호출 성공");
		int result = 
			sqlSessionTemplate.insert("deptInsert",pdVO);
		return result;
	}
	public List<DeptVO> deptList(Map<String, Object> pMap) {
		logger.info("deptList");
		List<DeptVO> deptList = null;
		deptList = sqlSessionTemplate.selectList("deptList", pMap);
		logger.info(deptList);
		return deptList;
	}
}




