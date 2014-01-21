package com.sanxian.sxzhuanhuan.function.login;

import android.os.Handler;
import android.os.Message;
import android.view.View;

/**   
 * @Title: TimerThread.java 
 * @Package com.sanxian.sxzhuanhuan.function.login 
 * @Description: TODO
 * @author zhangfl@sanxian.com
 * @date 2014-1-16 上午11:36:57 
 * @version V1.0   
 */
public class TimerThread extends Thread{

	private final static int MSG_TIME  = 0x01 ;
	private int time = 0 ;
	private int TIMELIMIT = 0 ;
	private View view = null ;
	
	private Handler timeHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case MSG_TIME :
					++time ;
					System.out.println("time---" + time );
					break ;
			}
		};
	} ;
	
	public TimerThread(int timelimit) {
		// TODO Auto-generated constructor stub
		this.TIMELIMIT = timelimit ;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			do {
				Thread.sleep(1000);
				
				Message msg=new Message() ;
	            msg.what = MSG_TIME ;
	            timeHandler.sendMessage(msg) ;
			} while (Thread.interrupted()==false  && time < TIMELIMIT) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
