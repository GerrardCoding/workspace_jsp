package com.erp;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.vo.DeptVO;
public class DeptLogic {
	Logger logger = Logger.getLogger(DeptLogic.class);
	public DeptDao deptDao = null;
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	public int deptInsert(DeptVO pdVO) {
		int result = deptDao.deptInsert(pdVO);
		return result;
	}
	public List<DeptVO> deptList(Map<String, Object> pMap) {
		logger.info("deptList");
		List<DeptVO> deptList = null;
		deptList = deptDao.deptList(pMap);
		logger.info(deptList);
		return deptList;
	}
}








