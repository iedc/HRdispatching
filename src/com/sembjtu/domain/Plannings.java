/**
 * 
 */
package com.sembjtu.domain;

import java.io.Serializable;

/**
 * @author edc
 * 劳务派遣人力资源计划的domain对象
 */
public class Plannings implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int plan_id;
	private String plan_name;
	private String project_id;
	private String job_list_ids;
	private String job_working_hours;
	private String job_durations;
	
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getJob_list_ids() {
		return job_list_ids;
	}
	public void setJob_list_ids(String job_list_ids) {
		this.job_list_ids = job_list_ids;
	}
	public String getJob_working_hours() {
		return job_working_hours;
	}
	public void setJob_working_hours(String job_working_hours) {
		this.job_working_hours = job_working_hours;
	}
	public String getJob_durations() {
		return job_durations;
	}
	public void setJob_durations(String job_durations) {
		this.job_durations = job_durations;
	}
}
