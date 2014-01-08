/**
 * 用来调用Lingo计算的接口
 * @author edc
 */
package com.sembjtu.util;

import com.lindo.Lingd11;

//@Repository(value="lingo")
public class CallLingo {

	// Load the Lingo JNI interface
	static {
		System.load("D:\\Lingo\\Lingj11.dll");
	}
	
	// Create a new Lingo object, which we will use to interface with Lingo
	Lingd11 lng = new Lingd11();

	// Stores the Lingo JNI environment pointer
	Object pnLngEnv;
	
	/* (non-Javadoc)
	 * @see com.sembjtu.util.CallLingo#setLingoModel(java.lang.Object)
	 */
	public Object setLingoModel(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sembjtu.util.CallLingo#lingoCalculate(java.lang.Object)
	 */
	public void lingoCalculate(Object obj) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.sembjtu.util.CallLingo#returnLingoResult(java.lang.Object)
	 */
	public Object returnLingoResult(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
