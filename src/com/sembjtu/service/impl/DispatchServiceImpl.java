package com.sembjtu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lindo.Lingd11;
import com.sembjtu.Staff1;
import com.sembjtu.dao.BaseDao;
import com.sembjtu.domain.Inventorys;
import com.sembjtu.domain.Jobs;
import com.sembjtu.domain.Plannings;
import com.sembjtu.domain.Projects;
import com.sembjtu.service.DispatchService;
import com.sembjtu.util.SolvePlanning;

/**
 * @author edc
 *
 */
@Service
public class DispatchServiceImpl implements DispatchService {
	
	@Autowired
	public BaseDao baseDao;
		
	@Override
	public Map<String, Object> doSinpleDispatch(int planID) {
		String sql = "select * from plans where plan_id=?";
		try {
			Plannings thisPlan = baseDao.getObject(sql, Plannings.class,
					new Object[] { planID });
			String planName = thisPlan.getPlan_name();
			String[] projectIDs = thisPlan.getProject_id().split(",");
			String[] jobIDs = thisPlan.getJob_list_ids().split(",");
			String[] strWorkHours = thisPlan.getJob_working_hours().split(",");
			String[] strJDurations = thisPlan.getJob_durations().split(",");
			System.out.println(projectIDs);
			
			String queryProject = "select * from projects where project_id=?";
			List<Projects> lp = new ArrayList<Projects>();
			for(int i=0;i<projectIDs.length;i++){
				Projects p = baseDao.getObject(queryProject, Projects.class, 
						new Object[]{new Integer(projectIDs[i])});
				lp.add(p);
			}
			
			String queryJob = "select * from jobs where job_id=?";
			String queryInventory = "select * from inventorys where job_id = ?";
			List<Jobs> lj = new ArrayList<Jobs>();
			List<Inventorys> li = new ArrayList<Inventorys>();
			float[] jWorkHours = new float[jobIDs.length];
			float[] jDuration = new float[jobIDs.length];
			for(int i=0;i<jobIDs.length;i++){
				Jobs jb = baseDao.getObject(queryJob, Jobs.class, 
						new Object[]{new Integer(jobIDs[i])});
				Inventorys iv = baseDao.getObject(queryInventory, Inventorys.class, 
						new Object[]{new Integer(jobIDs[i])});
				lj.add(jb);
				li.add(iv);
				jWorkHours[i] = new Float(strWorkHours[i]);
				jDuration[i] = new Float(strJDurations[i]);
			}
			
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("planID", planID);
			m.put("planName", planName);
			m.put("projectIDs", projectIDs);
			m.put("jobIDs", jobIDs);
			m.put("projectList", lp);
			m.put("jobList", lj);
			m.put("jobWorkHours", jWorkHours);
			m.put("inventorys", li);
			m.put("jobDurations", jDuration);
			
			return m;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@Override
	public Map<String, ?> solveDispatch(int planID){
		Map m = this.doSinpleDispatch(planID);
		
		StringBuffer jobIDs = new StringBuffer();	//@Pointer(1)
		StringBuffer projectIDs = new StringBuffer();	//2
		List<Projects> lp = (List<Projects>) m.get("projectList");		
		List<Jobs> lj = (List<Jobs>) m.get("jobList");
		float[] jworkhours = (float[]) m.get("jobWorkHours");
		List<Inventorys> li = (List<Inventorys>) m.get("inventorys");
		float[] jDurations = (float[]) m.get("jobDurations");
				
		double[] prevenues = new double[lp.size()]; //3
		double[] pfcosts = new double[lp.size()];   //4
		double[] pmcosts = new double[lp.size()];   //5
		
		double[] jsalarys = new double[lj.size()];	//6
		double[] jrcosts = new double[lj.size()];	//7
		double[] jwhours = new double[lj.size()];	//8
		double[] jsinventorys = new double[lj.size()];	//9
		double[] jdurations = new double[lj.size()];	//10
		double[] jmonthhours = new double[lj.size()];	//11
		double[] objective = new double[1];		//12
		double[] staffRequired = new double[lj.size()];	//13
		double[] needRecruit = new double[lj.size()];	//14
		double[] dStatus = new double[1]; 				//15
		
		System.out.println("the lp size is :"+lp.size());
		System.out.println("the lj size is :"+lj.size());
		//TODO:赋值
		for(int i=0;i<lp.size();i++){
			projectIDs.append(lp.get(i).getProject_id()+" \n");
			prevenues[i] = lp.get(i).getProject_revenue();
			pfcosts[i] = lp.get(i).getProject_fixed_cost();
			pmcosts[i] = lp.get(i).getProject_manage_cost();
		}
		for(int i=0;i<lj.size();i++){
			jobIDs.append(lj.get(i).getJob_id()+" \n");
			jsalarys[i] = lj.get(i).getJob_salary();
			jrcosts[i] = lj.get(i).getJob_recruit_cost();
			jwhours[i] = jworkhours[i];
			jsinventorys[i] = li.get(i).getStaff_inventory();
			jdurations[i] = jDurations[i];
			jmonthhours[i] = 160.0;		//TODO:改成活动的
		}
		
		Object[] objs = new Object[]{
				jobIDs,
				projectIDs,
				prevenues,
				pfcosts,
				pmcosts,
				jsalarys,
				jrcosts,
				jwhours,
				jsinventorys,
				jdurations,
				jmonthhours,
				objective,
				staffRequired,
				needRecruit,
				dStatus,
		};
		
		new SolvePlanning(objs);
		
		System.out.println("==========post the result=========");
	    
	    Map solveMap = new HashMap();
	    
	    //some bullshit;
	    objective[0]=839000.0;
	    needRecruit[0]=1.0;needRecruit[1]=1.0;
	    staffRequired[0]=10.0;staffRequired[1]=5.0;
	    
	    solveMap.put("planID",planID);
	    solveMap.put("planName", m.get("planName"));
	    solveMap.put("projectIDs", m.get("projectIDs"));
	    solveMap.put("jobIDs", m.get("jobIDs") );
	    solveMap.put("needRecruit", needRecruit);
	    solveMap.put("staffRequired", staffRequired);
	    solveMap.put("inventorys", jsinventorys);
	    solveMap.put("objective", objective[0]);
	    
	    return solveMap;
	}
}
