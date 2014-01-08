package com.sembjtu.service;

import java.util.List;

import com.sembjtu.domain.Projects;

public interface HRPlanningService {
	
	/**
	 * 列出所有项目
	 * @return 包含所有项目的List
	 */
	List<?> listAllProjects();
	
	/**
	 * 基于project_id获得单个项目
	 * @param pid	项目的ID
	 * @return
	 */
	Projects getSingleProject(int pid);

	/**
	 * 添加单个项目
	 * @param pid
	 * @param pname
	 * @param psdate
	 * @param duration
	 * @param revenue
	 * @param fcost
	 * @param mcost
	 */
	void addProject(int pid, String pname, String psdate, int duration, 
			float revenue, float fcost, float mcost);
	/**
	 * 删除单个项目
	 * @param pid
	 */
	void deleteProject(int pid);
	
	/**
	 * 修改单个项目
	 * @param pid
	 * @param pname
	 * @param psdate
	 * @param duration
	 * @param revenue
	 * @param fcost
	 * @param mcost
	 */
	void updateProject(int pid, String pname, String psdate, int duration, 
			float revenue, float fcost, float mcost);
	
	/**
	 * 列出所有工种
	 * @return 一个包含所有工种的List
	 */
	List<?> listAllJobs();
	
	/**
	 * 添加单个工种
	 * @param jid
	 * @param jname
	 * @param jsalary
	 * @param jrcost
	 */
	void addJob(int jid, String jname, float jsalary, float jrcost);
	
	
	/**
	 * 删除单个工种
	 * @param jid
	 */
	void deleteSingleJob(int jid);
	
	/**
	 * 修改单个工种
	 * @param jid
	 * @param jname
	 * @param jsalary
	 * @param jrcost
	 */
	void updateSingleJob(int jid, String jname, float jsalary, float jrcost);
	
	/**
	 * 列出所有项目
	 * @return
	 */
	List<?> listAllPlanning();
	
	/**
	 * 列出单个项目
	 * @param planID
	 * @return
	 */
	List<?> listSinglePlanning(int planID);
	
	/**
	 * 添加单个项目
	 * @param planID
	 * @param pname
	 * @param projectIDs
	 * @param jobIDs
	 * @param jobHours
	 */
	void addSinglePlanning(int planID, String pname, String projectIDs, String jobIDs, String jobHours, String jobDurations);
	
	/**
	 * 删除单个项目
	 * @param planID
	 */
	void deleteSinglePlanning(int planID);
	
	/**
	 * 修改单个项目
	 * @param planID
	 * @param pname
	 * @param projectIDs
	 * @param jobIDs
	 * @param jobHours
	 * @param jobDurations
	 */
	void updateSinglePlanning(int planID, String pname, String projectIDs, String jobIDs, String jobHours, String jobDurations);
}
