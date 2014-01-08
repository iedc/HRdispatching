/**
 * 
 */
package com.sembjtu.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sembjtu.service.DispatchService;
import com.sembjtu.service.HRPlanningService;
import com.sembjtu.web.BaseController;

/**
 * @author edc
 *
 */
@Controller
public class DispatchController extends BaseController {
	
	@Autowired
	private DispatchService dispatchService;
	@Autowired
	private HRPlanningService hrpPlanningService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="listDispatch.do")
	public String listDispatch(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws IOException, ServletException {
		List planlist = hrpPlanningService.listAllPlanning();
		model.addAttribute("planlist", planlist);
		return "dispatch/planningDispatch";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="solveSinglePlanning.do")
	public String solveSinglePlanning(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		int planID = new Integer(request.getParameter("solvePlanID"));
		Map m = dispatchService.solveDispatch(planID);
//		dispatchService.solveTest();
		System.out.println(m.keySet());
		model.addAllAttributes(m);
		return "dispatch/dispatchResult";
	}
}
