package com.queue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue��Ϣ��������
 * @author pangpeijie
 *
 */
public class QueueContainer {
	
	private static QueueContainer instance = null;

	/**
     * ��Ϣ����
     */
    private static final BlockingQueue<DataBean> MESSAGE_QUEUE = new LinkedBlockingQueue<DataBean>();
	
    /**
     * ����ģʽ�������߳�ȥ��̨�ύ����
     * @return
     */
    public static QueueContainer getInstance() {
        if (instance == null) {
        	instance = new QueueContainer();
        	Thread notifierThread = new Thread(notifierRunnable);
            notifierThread.start();
        }
        return instance;
     }

    
    /**
     * ����bean���첽�߳�
     * @param bean
     * @throws IOException
     */
    public void sendData(DataBean bean) throws IOException {
        try {
            MESSAGE_QUEUE.put(bean);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            System.out.println(formatter.format(new Date())+"--push ���ݵ����У�"+bean.getWebServiceName());
        } catch (Exception ex) {
            IOException t = new IOException();
            t.initCause(ex);
            throw t;
        }
    }
    
    /**
     * �첽�̣߳��������б��������ݣ����ͷ�take����������,�ύwebservice�ӿ�
     */
    public static Runnable notifierRunnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			boolean done = false;
            while (!done) {
            	DataBean temp = null;
                try {
                	//��һ����֤�����Ƿ��������ж�
                	//todo
                	
                	temp = MESSAGE_QUEUE.take();
                	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
                    System.out.println(formatter.format(new Date())+"--��������ȥ�����ύwebservice�ӿڣ�"+temp.getWebServiceName());
                    //��������ȥ�����ύwebservice�ӿ�
                    //todo
                    
                    
                } catch (InterruptedException iex) {
                    done = true;
                    System.out.println(iex);
                }
            }
		}
    };
    
    
    
}
