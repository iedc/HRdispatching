/**
 * 
 */
package com.sembjtu.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sembjtu.dao.BaseDao;
import com.sembjtu.domain.Jobs;
import com.sembjtu.domain.Plannings;
import com.sembjtu.domain.Projects;
import com.sembjtu.domain.User;
import com.sembjtu.service.HRPlanningService;

/**
 * @author edc
 * @author sem.bjtu.edu.cn
 */
@Service
public class HRPlanningServiceImpl implements HRPlanningService {
	
	@Autowired
	public BaseDao baseDao;

	@Override
	public List<Projects> listAllProjects() {
		try {
			String sql = "select * from projects";
			List<Projects> list = baseDao.getObjList(sql);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Projects getSingleProject(int pid) {
		try {
			String sql = "select * from projects where project_id=?";
			Projects p = baseDao.getObject(sql, Projects.class,
					new Object[] { pid });
			System.out.println("the p is : "+p);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void addProject(int pid, String pname, String psdate, int duration,
			float revenue, float fcost, float mcost) {
		Projects p = new Projects();
		
		//TODO:判断ID是否存在
		
		p.setProject_id(pid);
		p.setProject_name(pname);
		p.setStart_date(psdate);
		p.setProject_duration(duration);
		p.setProject_revenue(revenue);
		p.setProject_fixed_cost(fcost);
		p.setProject_manage_cost(mcost);
		
		Calendar cldr = Calendar.getInstance();
		int ym = cldr.get(Calendar.YEAR)*100+cldr.get(Calendar.MONTH)+1;
		String pstatus;
		if(ym <= new Integer(psdate)){
			pstatus = "未开始";
			p.setProject_status(pstatus);
		}else if(ym >= new Integer(psdate) && ym <= new Integer(psdate)+duration){
			pstatus = "已开始";
			p.setProject_status(pstatus);
		}else{
			pstatus = "已结束";
			p.setProject_status(pstatus);
		}
		
		String sqlInsert = "insert into projects(project_id, project_name, " +
				"start_date, project_duration, project_status, " +
				"project_revenue, project_fixed_cost, project_manage_cost)" + 
				"values('" + pid + "','" + pname + "','" + psdate + "'," +
				"'" + duration + "','" + pstatus + "','" + revenue + "'," +
				"'" + fcost + "','" + mcost + "')";
		System.out.println(sqlInsert);
		try {
			baseDao.saveOrUpdateObject(sqlInsert, p);
		} catch (Exception e) {
			System.err.println("数据库操作失败！");
		}
	}

	@Override
	public void deleteProject(int pid) {
		String sqlDel = "delete from projects where project_id = '" + pid + "'";
		try {
			baseDao.editObject(sqlDel, null);
		} catch (Exception e) {
			System.err.println("数据库操作失败！");
		}
		
	}

	@Override
	public void updateProject(int pid, String pname, String psdate,
			int duration, float revenue, float fcost, float mcost) {
		
		String sqlUpdate = "update projects set project_name=?, " +
				"start_date=?, project_duration=?, project_status=?, " +
				"project_revenue=?, " +
				"project_fixed_cost=?, project_manage_cost=? " +
				"where project_id=?";
		
		Calendar cldr = Calendar.getInstance();
		int ym = cldr.get(Calendar.YEAR)*100+cldr.get(Calendar.MONTH)+1;
		String pstatus;
		if(ym <= new Integer(psdate)){
			pstatus = "未开始";
		}else if(ym >= new Integer(psdate) && ym <= new Integer(psdate)+duration){
			pstatus = "已开始";
		}else{
			pstatus = "已结束";
		}
		Object[] objs = new Object[]{pname,psdate,duration,pstatus,	revenue,fcost,mcost,pid};
		try {
			baseDao.editObject(sqlUpdate, objs);
			System.out.println("------ update Project ------ Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<?> listAllJobs() {
		try {
			String sql = "select * from jobs";
			List<Jobs> list = baseDao.getObjList(sql);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addJob(int jid, String jname, float jsalary, float jrcost) {
		
		//TODO:判断jid是否存在。
		
		String sql = "insert into jobs(job_id, job_name, " +
				"job_salary, job_recruit_cost) values(?,?,?,?)";
		try {
			baseDao.editObject(sql,
					new Object[] { jid, jname, jsalary, jrcost });
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void deleteSingleJob(int jid) {
		String sql = "delete from jobs where job_id=?";
		try {
			baseDao.editObject(sql, new Object[] { jid });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSingleJob(int jid, String jname, float jsalary,
			float jrcost) {
		String sqlUpdate = "update jobs set job_name=?, " +
				"job_salary=?, job_recruit_cost=? where job_id=?";
		try {
			baseDao.editObject(sqlUpdate, new Object[] { jname, jsalary,
					jrcost, jid });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<?> listAllPlanning() {
		try {
			String sqlListAll = "select * from plans";
			List<Plannings> list = baseDao.getObjList(sqlListAll);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> listSinglePlanning(int planID) {
		try {
			String sqlListSingle = "select * from plans where plan_id=?";
			List<Plannings> list = baseDao.getObjList(sqlListSingle,
					Plannings.class, new Object[] { planID });
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addSinglePlanning(int planID, String pname, String projectIDs,
			String jobIDs, String jobHours, String jobDurations) {
		try {
			String sqlUpdate = "insert into plans(plan_id, plan_name, " +
					"project_id, job_list_ids, job_working_hours, job_durations) " +
					"values(?,?,?,?,?,?)";
			baseDao.editObject(sqlUpdate, 
					new Object[]{planID,pname,projectIDs,jobIDs,jobHours,jobDurations});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteSinglePlanning(int planID) {
		try {
			String sqlSingleDel = "delete from plans where plan_id=?";
			baseDao.editObject(sqlSingleDel, new Object[] { planID });
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateSinglePlanning(int planID, String pname,
			String projectIDs, String jobIDs, String jobHours, String jobDurations) {
		try {
			String sqlUpdate = "update plans set plan_name=?, project_id=?, " +
					"job_list_ids=?, job_working_hours=?, job_durations=? " +
					"where plan_id=?";
			baseDao.editObject(sqlUpdate, new Object[] { pname, projectIDs,
					jobIDs, jobHours, jobDurations, planID });
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
