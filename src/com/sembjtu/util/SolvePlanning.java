/**
 * 
 */
package com.sembjtu.util;

import com.lindo.Lingd13;

/**
 * @author edc
 *
 */
public class SolvePlanning{

	static {
		System.load("F:\\LINGO64_13\\Lingj64_13.dll");
	}
	
	Lingd13 lng = new Lingd13();
	Object pnLngEnv = new Object();
	
	int nLastIterationCount;
	
	public SolvePlanning(Object[] objs){
		goSolve(objs);
	}
	
	private static int MySolverCallBack(Object pnLng, int iLoc, Object jobj){
		SolvePlanning sp = (SolvePlanning) jobj;
		int nIterations[] = new int [1];
		sp.lng.LSgetCallbackInfoIntLng(pnLng, Lingd13.LS_IINFO_ITERATIONS_LNG, nIterations);
		if(nIterations[0] != sp.nLastIterationCount){
			sp.nLastIterationCount = nIterations[0];
			 System.out.println("In Java callback function...iterations = " + sp.nLastIterationCount);
		}
		return (0);
	}
	
	@SuppressWarnings("static-access")
	private void goSolve(Object[] objs){
		
		
		int nErr = -1;
		
		Object[] anotherObjs = objs;
		//create the Lingo environment
		pnLngEnv = lng.LScreateEnvLng();
		if(pnLngEnv == null){
		     System.out.println( "***Unable to create Lingo environment***");
//		     return null;
		     return;
		}
		// open a log file	      
		nErr = lng.LSopenLogFileLng( pnLngEnv, "lingo2.log");
	    if ( nErr != lng.LSERR_NO_ERROR_LNG ){
	         System.out.println( "***LSopenLogFileLng() error***: " + nErr);
//	         return null;
	         return;
	    }
	    //System.out.println(nPointersNow);
	    // pass lingo a pointer to the JOBS KIND set
	    int[] nPointersNow = new int[1];
	    
	    for(int i=0;i<anotherObjs.length;i++){
	    	if(anotherObjs[i].getClass().equals(StringBuffer.class)){
	    		System.out.println("-------------The obj["+i+"] is StringBuffer!---------");
	    		nErr = lng.LSsetPointerStringLng(pnLngEnv, (StringBuffer) anotherObjs[i], nPointersNow);
	    	}else if(anotherObjs[i].getClass().equals(double[].class)){
	    		System.out.println("-------------The obj["+i+"] is double[]!-------------");
	    		nErr = lng.LSsetPointerLng(pnLngEnv, (double[]) anotherObjs[i], nPointersNow);
	    	}
	    	System.out.println(nPointersNow[0]);
	    	if ( nErr != lng.LSERR_NO_ERROR_LNG ){
		         System.out.println( "***LSsetPointerStringLng() error***: " + nErr);
		         break;
		    }
	    }
	    
	    //pass Lingo the name of the solver callback function...
//	    nErr = lng.LSsetCallbackSolverLng( pnLngEnv, "MySolverCallback", null);
		// construct the script
		  
		// echo input to log file
		String sScript = "SET ECHOIN 1" + "\n";
		// load the model from disk
		sScript = sScript + "TAKE WebContent\\lingo-model-file\\HRdispatch_planning_mdl.lng" + "\n";
		// view the formulation
		sScript = sScript + "LOOK ALL" + "\n";
		// solve
		sScript = sScript + "GO" + "\n";
		// exit script processor
		sScript = sScript + "QUIT" + "\n";
		
		nErr = lng.LSexecuteScriptLng( pnLngEnv, sScript);
		System.out.println("execute LINGO sCcript over!");
		if ( nErr != lng.LSERR_NO_ERROR_LNG ){
		     System.out.println( "***LSexecuteScriptLng error***: " + nErr);
//		     return null;
		     return;
		}
		
		// clear the pointers to force update of local arrays
		// ***NOTE*** solution won't get passed to local arrays until
		// LSclearPointersLng is called!!!
		nErr = lng.LSclearPointersLng( pnLngEnv);
		if ( nErr != lng.LSERR_NO_ERROR_LNG ){
		     System.out.println( "***LSclearPointerLng() error***: " + nErr);
//		     return null;
		     return;
		}
		
//				System.out.println("now the dStatus = "+dStatus[0]);
//				if ( dStatus[0] != lng.LS_STATUS_GLOBAL_LNG) 
//				     System.out.println( "***Unable to Solve*** dStatus:" + dStatus[0]);
		      
		// close Lingo's log file
		nErr = lng.LScloseLogFileLng( pnLngEnv);
	    if ( nErr != lng.LSERR_NO_ERROR_LNG ){
	         System.out.println( "***LScloseLogFileLng() error***: " + nErr);
//	         return null;
	         return;
	    }
		
	    // delete the Lingo environment
	    lng.LSdeleteEnvLng( pnLngEnv);
	    
	    //TODO:post the result.
//			    System.out.println("===============The obj is: " + objective[0] + "==============");
//			    for(int i = 0;i<lj.size();i++){
//			    	Double d = staffRequired[i];
//			    	Integer n = d.intValue();
//			    	System.out.println("The ith job requires " + n + " persons.");
//			    }
		
	    for(Object obj:anotherObjs){
	    	if(obj instanceof StringBuffer){
	    		System.out.print(obj);
	    	}else if(obj instanceof double[]){
	    		for(double d:(double[])obj){
	    			System.out.print(d+" ");
	    		}
	    	}
			System.out.println("");
	    }
//		return anotherObjs;
		
	}
	
	public static void main(String[] args){
		Object[] objs = new Object[]{
			new StringBuffer("50000003\n 50000005\n "),
			new StringBuffer("10101010\n"),
			new double[]{1000000.0},
			new double[]{200000.0},
			new double[]{2000.0},
			new double[]{10000.0, 20000.0},
			new double[]{3000.0, 3000.0},
			new double[]{4800.0, 2400.0},
			new double[]{10.0, 2.0},
			new double[]{3.0,3.0},
			new double[]{160.0, 160.0},
			new double[1],
			new double[2],
			new double[2],
			new double[1]
		};
		new SolvePlanning(objs);
	}
}
