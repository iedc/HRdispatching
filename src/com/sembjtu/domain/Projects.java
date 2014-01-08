/**
 * 
 */
package com.sembjtu.domain;

import java.io.Serializable;

/**
 * @author edc
 *
 */
public class Projects implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int project_id;
	private String project_name;
	private String start_date;
	private int project_duration;
	private String project_status;
	private float project_revenue;
	private float project_fixed_cost;
	private float project_manage_cost;
	private int project_weight;
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public int getProject_duration() {
		return project_duration;
	}
	public void setProject_duration(int project_duration) {
		this.project_duration = project_duration;
	}
	public String getProject_status() {
		return project_status;
	}
	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}
	public float getProject_revenue() {
		return project_revenue;
	}
	public void setProject_revenue(float project_revenue) {
		this.project_revenue = project_revenue;
	}
	public float getProject_fixed_cost() {
		return project_fixed_cost;
	}
	public void setProject_fixed_cost(float project_fixed_cost) {
		this.project_fixed_cost = project_fixed_cost;
	}
	public float getProject_manage_cost() {
		return project_manage_cost;
	}
	public void setProject_manage_cost(float project_manage_cost) {
		this.project_manage_cost = project_manage_cost;
	}
	public int getProject_weight() {
		return project_weight;
	}
	public void setProject_weight(int project_weight) {
		this.project_weight = project_weight;
	}
	
}
