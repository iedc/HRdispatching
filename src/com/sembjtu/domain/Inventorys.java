/**
 * 
 */
package com.sembjtu.domain;

import java.io.Serializable;

/**
 * @author edc
 *
 */
public class Inventorys implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int job_id;
	private int staff_inventory;
	
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public int getStaff_inventory() {
		return staff_inventory;
	}
	public void setStaff_inventory(int staff_inventory) {
		this.staff_inventory = staff_inventory;
	}
	
	
	
}
