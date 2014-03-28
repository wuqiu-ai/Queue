package com.queue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue消息队列容器
 * @author pangpeijie
 *
 */
public class QueueContainer {
	
	private static QueueContainer instance = null;

	/**
     * 消息队列
     */
    private static final BlockingQueue<DataBean> MESSAGE_QUEUE = new LinkedBlockingQueue<DataBean>();
	
    /**
     * 单例模式，启动线程去后台提交数据
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
     * 发送bean到异步线程
     * @param bean
     * @throws IOException
     */
    public void sendData(DataBean bean) throws IOException {
        try {
            MESSAGE_QUEUE.put(bean);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            System.out.println(formatter.format(new Date())+"--push 数据到队列："+bean.getWebServiceName());
        } catch (Exception ex) {
            IOException t = new IOException();
            t.initCause(ex);
            throw t;
        }
    }
    
    /**
     * 异步线程，当队列中被放入数据，将释放take方法的阻塞,提交webservice接口
     */
    public static Runnable notifierRunnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			boolean done = false;
            while (!done) {
            	DataBean temp = null;
                try {
                	//加一个验证网络是否正常的判断
                	//todo
                	
                	temp = MESSAGE_QUEUE.take();
                	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
                    System.out.println(formatter.format(new Date())+"--接下来就去操作提交webservice接口："+temp.getWebServiceName());
                    //接下来就去操作提交webservice接口
                    //todo
                    
                    
                } catch (InterruptedException iex) {
                    done = true;
                    System.out.println(iex);
                }
            }
		}
    };
    
    
    
}
