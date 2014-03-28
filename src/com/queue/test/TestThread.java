package com.queue.test;

import java.io.IOException;

import com.queue.DataBean;
import com.queue.QueueContainer;

/**
 * ����BlockingQueue����
 * @author pangpeijie
 *
 */
public class TestThread extends Thread{

	public static void main(String[] args){
		TestThread testThread = new TestThread();
		testThread.start();
		
		QueueContainer container = QueueContainer.getInstance();
		
		//���߳��ύ���ݵ�����
		try{
			for(int i = 1;i <= 100;i++){
	             //��ʱ1��
	             Thread.sleep(1000);
	             DataBean bean = new DataBean();
	             bean.setWebServiceName("test webServiceName"+i);
	             container.sendData(bean);
			 }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		 
		
	}
	
	


	
	
}
