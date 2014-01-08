/**
 * 
 */
package com.sembjtu.domain;

import java.io.Serializable;

/**
 * @author edc
 *
 */
public class Jobs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int job_id;
	private String job_name;
	private float job_salary;
	private float job_recruit_cost;
	
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public float getJob_salary() {
		return job_salary;
	}
	public void setJob_salary(float job_salary) {
		this.job_salary = job_salary;
	}
	public float getJob_recruit_cost() {
		return job_recruit_cost;
	}
	public void setJob_recruit_cost(float job_recruit_cost) {
		this.job_recruit_cost = job_recruit_cost;
	}

}
