package com.erp;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import com.vo.DeptVO;
import com.vo.EmpVO;
public class EmpLogic {
	Logger logger = Logger.getLogger(EmpLogic.class);
	public EmpDaoImpl empDao = null;
	public void setEmpDao(EmpDaoImpl empDao) {
		this.empDao = empDao;
	}
	public DeptDao deptDao = null;
	public void setDeptDao(DeptDao deptDao)
	{
		this.deptDao = deptDao;
	}
	//@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={DataAccessException.class})
	//@Pointcut(value="execution(* com.erp.*Logic.*(..)")
	//@Transactional
	public int goEmp(EmpVO peVO) {
	//public int cudEmp(EmpVO peVO) {
		logger.info("doEmp");
		int result = 0;
		peVO.setEmpno(9003);
		peVO.setEname("나종일");
		peVO.setMgr(7902);
		peVO.setJob("CLERK");
		peVO.setHiredate("");
		peVO.setSal(2300);
		peVO.setComm(500);
		peVO.setDeptno(90);
		try {
			
			DeptVO pdVO = new DeptVO();
			pdVO.setDeptno(90);
			pdVO.setDname("총무부");
			pdVO.setLoc("부산");
			deptDao.deptInsert(pdVO);
			result = empDao.empInsert(peVO);
		} catch (DataAccessException de) {
			throw de;
		}
		return result;
	}
}








