/**
 * 
 */
package com.sembjtu.util;

import org.springframework.core.NestedRuntimeException;

/**
 * 在数据库操作异常时抛出该异常，在controller层进行处理。
 * @author edc
 * @author sem.bjtu.edu.cn
 */
public class DAOException extends NestedRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(String msg) {
		super(msg);
	}
	
	public DAOException(String msg,Throwable obj) {
		super(msg,obj);
	}
}

