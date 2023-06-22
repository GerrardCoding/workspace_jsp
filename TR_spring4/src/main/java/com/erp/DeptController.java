package com.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.util.HangulConversion;
import com.util.HashMapBinder;
import com.vo.DeptVO;

public class DeptController extends MultiActionController{
	Logger logger = 
			Logger.getLogger(DeptController.class);
	public DeptLogic deptLogic = null;
	public void setDeptLogic(DeptLogic deptLogic) {
		this.deptLogic = deptLogic;
	}
	public String deptInsert(HttpServletRequest req,HttpServletResponse res)
	{
		DeptVO pdVO = new DeptVO();
		pdVO.setDeptno
		(Integer.parseInt(req.getParameter("deptno")));
		pdVO.setDname
		(HangulConversion.toKor(req.getParameter("dname")));
		pdVO.setLoc
		(HangulConversion.toKor(req.getParameter("loc")));
		int result = deptLogic.deptInsert(pdVO);
		return "dept/getDeptList";
	}
	public ModelAndView deptList(HttpServletRequest req,HttpServletResponse res)
	{
		logger.info("deptList");
		HashMapBinder hmb = new HashMapBinder(req);
		Map<String,Object> pMap = new HashMap<>();
		hmb.bind(pMap);
		List<DeptVO> deptList = deptLogic.deptList(pMap);
		//logger.info(deptList);
		//스프링에서 UI를 지원하는 클래스임
		ModelAndView mav = new ModelAndView();
		mav.addObject("deptList", deptList);
		//ViewResolver개입
		//-> /WEB-INF/views(자동으로 스프링에서 붙여준다) 화면이름.jsp
		//-> /WEB-INF/views/dept/getDeptList.jsp
		mav.setViewName("dept/getDeptList");
		//return "forward:getDeptList.jsp";
		return mav;
	}
}





















