/**
 * 
 */
package com.sembjtu.service;

import java.util.Map;

/**
 * @author edc
 *
 */
public interface DispatchService {
	/**
	 * 将一堆派遣的东西全部装进一个map里面
	 * @param planID
	 * @return 
	 */
	Map<?,?> doSinpleDispatch(int planID);
	
	/**
	 * 对这某个规划进行求解
	 * @param planID
	 */
	Map<?,?> solveDispatch(int planID);

}
