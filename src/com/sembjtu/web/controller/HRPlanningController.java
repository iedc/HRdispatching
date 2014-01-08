/**
 * 
 */
package com.sembjtu.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sembjtu.domain.Projects;
import com.sembjtu.service.HRPlanningService;
import com.sembjtu.util.ResponseUtils;
import com.sembjtu.web.BaseController;

/**
 * @author edc
 * @author sem.bjtu.edu.cn
 * 
 */
@Controller
public class HRPlanningController extends BaseController {
	
	@Autowired
	private HRPlanningService hrpPlanningService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="listProject.do")
	public String listProject(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model){
		try {
			List list = hrpPlanningService.listAllProjects();
			model.addAttribute("list", list);
			System.out.println(model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "planning/projectlist";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="editProject.do")
	public String editProject(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model){
		try {
			List list = hrpPlanningService.listAllProjects();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "planning/editProject";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="addProject.do")
	public String addProject(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		int pid = new Integer(request.getParameter("pid"));
		String pname = request.getParameter("pname");
		String psdate = request.getParameter("psdate");
		int duration = new Integer(request.getParameter("pduration"));
		float revenue = new Float(request.getParameter("revenue"));
		float fcost = new Float(request.getParameter("fcost"));
		float mcost = new Float(request.getParameter("mcost"));
		System.err.println(pid+" "+pname+" "+psdate+" "+duration+" "+revenue+" "+fcost+" "+mcost);
		hrpPlanningService.addProject(pid, pname, psdate, duration, revenue, fcost, mcost);
		
		try {
			List list = hrpPlanningService.listAllProjects();
			model.addAttribute("list", list);
			System.out.println(model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "planning/editProject";
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="deleteProject.do")
	public String deleteProject(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		int pid = new Integer(request.getParameter("pid"));
		try {
			hrpPlanningService.deleteProject(pid);
			System.out.println("删除成功");
			List list = hrpPlanningService.listAllProjects();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("删除不成功！");
		}
		return "planning/editProject";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="updateProject.do")
	public String updateProject(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		int pid = new Integer(request.getParameter("uh_pid"));
		model.addAttribute("projectID", pid);
		
		String pname = request.getParameter("u_pname");
		String psdate = request.getParameter("u_psdate");
		int duration = new Integer(request.getParameter("u_pduration"));
		float revenue = new Float(request.getParameter("u_revenue"));
		float fcost = new Float(request.getParameter("u_fcost"));
		float mcost = new Float(request.getParameter("u_mcost"));
		hrpPlanningService.updateProject(pid, pname, psdate, duration, revenue, fcost, mcost);
		
		try {
			List list = hrpPlanningService.listAllProjects();
			model.addAttribute("list", list);
			System.out.println(model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "planning/editProject";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="editJob.do")
	public String listJobs(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		List joblist = hrpPlanningService.listAllJobs();
		model.addAttribute("joblist", joblist);
		return "planning/joblist";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="addSingleJob.do")
	public String addSingleJob(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		int jid = new Integer(request.getParameter("jid"));
		String jname = request.getParameter("jname");
		float jsalary = new Float(request.getParameter("jsalary"));
		float jrcost = new Float(request.getParameter("jrcost"));
		hrpPlanningService.addJob(jid, jname, jsalary, jrcost);
		
		List joblist = hrpPlanningService.listAllJobs();
		model.addAttribute("joblist", joblist);
		return "planning/joblist";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="editSingleJob.do")
	public String editSingleJob(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		//"u" stands for "update" while "h" stands for "hidden";
		int jid = new Integer(request.getParameter("uh_jid"));
		String jname = request.getParameter("u_jname");
		float jsalary = new Float(request.getParameter("u_jsalary"));
		float jrcost = new Float(request.getParameter("u_jrcost"));
		hrpPlanningService.updateSingleJob(jid, jname, jsalary, jrcost);
		
		List joblist = hrpPlanningService.listAllJobs();
		model.addAttribute("joblist", joblist);
		return "planning/joblist";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="deleteSingleJob.do")
	public String deleteSingleJob(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		int jid = new Integer(request.getParameter("jid"));
		hrpPlanningService.deleteSingleJob(jid);
		
		List joblist = hrpPlanningService.listAllJobs();
		model.addAttribute("joblist", joblist);
		return "planning/joblist";
	}
	
	/**
	 * 派遣项目相关Controller，edc于2013年8月27日添加
	 * @author edc
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="listPlanning.do")
	public String listPlan(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		//TODO:显示计划的具体内容。
		List planlist = hrpPlanningService.listAllPlanning();
		model.addAttribute("planlist", planlist);
		return "planning/planningList";
	}
	
	@RequestMapping(value="addPlanning.do")
	public String addPlanning(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		List joblist = hrpPlanningService.listAllJobs();
		List plist = hrpPlanningService.listAllProjects();
		model.addAttribute("joblist", joblist);
		model.addAttribute("plist",plist);
		
		return "planning/addPlan";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="deleteSinglePlan.do")
	public String deleteSinglePlan(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		int planID = new Integer(request.getParameter("planID"));
		hrpPlanningService.deleteSinglePlanning(planID);
		
		List planlist = hrpPlanningService.listAllPlanning();
		model.addAttribute("planlist", planlist);
		return "planning/planningList";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="getSingleProjectDetail.do")
	public void getSingleProjectDetail(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		int pid= new Integer(request.getParameter("pid"));
		Projects singleProject = hrpPlanningService.getSingleProject(pid);
		int projectID = singleProject.getProject_id();
		String pname = singleProject.getProject_name();
		float revenue = singleProject.getProject_revenue();
		float fcost = singleProject.getProject_fixed_cost();
		float mcost = singleProject.getProject_manage_cost();
		int pw = singleProject.getProject_weight();

		Map m = new HashMap();
		m.put("projectID", projectID);
		m.put("pname", pname);
		m.put("revenue", revenue);
		m.put("fcost", fcost);
		m.put("mcost", mcost);
		m.put("pw", pw);
		
		ResponseUtils.printMapToJson(response, m);
//		response.setContentType("json");
//		PrintWriter pw = response.getWriter();
//		pw.write(m.toString());
//		pw.close();
//		model.addAttribute("singleProject", singleProject);
//		model.addAttribute("projectID", projectID);
//		model.addAttribute("pname", pname);
//		model.addAttribute("revenue", revenue);
//		model.addAttribute("fcost", fcost);
//		model.addAttribute("mcost", mcost);
	} 
	
	@RequestMapping(value="addProjectToPlan.do")
	public String addProjectToPlan(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		
		List joblist = hrpPlanningService.listAllJobs();
		List plist = hrpPlanningService.listAllProjects();
		model.addAttribute("joblist", joblist);
		model.addAttribute("plist",plist);
		return "planning/addPlan";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="doAddPlan.do")
	public String doAddPlan(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		int planID = new Integer(request.getParameter("add_pid"));
		String planName = request.getParameter("add_pname");
		String pids = request.getParameter("pids");
		String jps = request.getParameter("jids");
		String hours = request.getParameter("hours");
		String jds = request.getParameter("durations");
		hrpPlanningService.addSinglePlanning(planID, planName, pids, jps, hours, jds);
		
		List planlist = hrpPlanningService.listAllPlanning();
		model.addAttribute("planlist", planlist);
		return "planning/planningList";
	}
}