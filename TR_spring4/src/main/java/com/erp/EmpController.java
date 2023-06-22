package com.erp;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.util.HangulConversion;
import com.vo.EmpVO;

public class EmpController extends MultiActionController{
	Logger logger = 
			Logger.getLogger(EmpController.class);
	public EmpLogic empLogic = null;  
	public void setEmpLogic(EmpLogic empLogic) {
		this.empLogic = empLogic;
	}
	///erp/doEmp.sp4
	public String doEmp(HttpServletRequest req,HttpServletResponse res) throws Exception
	{  
		logger.info("doEmp호출 성공");
//		int result = empLogic.empInsert(peVO);
		EmpVO peVO = new EmpVO();
//		int result = empLogic.doEmp(peVO);
//		int result = empLogic.cudEmp(peVO);
		int result = empLogic.goEmp(peVO);
		return "getEmpList";
	}
}





















