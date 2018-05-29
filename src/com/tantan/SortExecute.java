/*
 * Copyright 2005-2017zbj.com All Rights Reserved.
 */
package com.tantan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程的时序性.<br>
 * 两种方法：一种使用单线程队列、一种使用线程join
 * @author tantan <br>
 * @version 1.0.0 2018年5月29日<br>
 * @see
 * @since JDK 1.7.0_79
 */
public class SortExecute {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		//方法一、使用单线程池
		ThreadFactory threadFactory=new MyThreadFactory();
		ExecutorService executor=Executors.newSingleThreadExecutor(threadFactory);
		executor.submit(new A());
		executor.submit(new B());
		executor.submit(new C());
		executor.submit(new D());
		executor.shutdown();
		
		System.out.println("--------------------------------------");
		//方法二、使用join 使用主线程join
		AA aa=new AA();
		BB bb=new BB();
		CC cc=new CC();
		DD dd=new DD();
		aa.start();
		aa.join();
        bb.start();    
        bb.join();
        cc.start();
        cc.join();
        dd.start();  
        Thread.sleep(1000);
		System.out.println("--------------------------------------");
		//方法二、使用join 使用执行线程join
		T1 t1=new T1(null);
		T2 t2=new T2(t1);
		T3 t3=new T3(t2);
		T4 t4=new T4(t3);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
			
		
	}
	
	static class MyThreadFactory implements ThreadFactory{
		AtomicInteger num=new AtomicInteger();

		@Override
		public Thread newThread(Runnable r) {
			Thread t=new Thread(r);
			t.setName("multiThreadExecuteBySort-pool-"+num.getAndAdd(1)+" ");
			t.setDaemon(true);
			return t;
		}
		
	}
	

	static class A implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"A");
		}

	}

	static class B implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"B");
		}

	}

	static class C implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"C");
		}

	}

	static class D implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"D");
		}

	}

	
	static class AA extends Thread {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"AA");
		}

	}

	static class BB extends Thread {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"BB");
		}

	}

	static class CC extends Thread {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"CC");
		}

	}

	static class DD extends Thread {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"DD");
		}

	}
	
	static class T1 extends Thread {
		Thread thread;
		

		public T1(Thread thread) {
			super();
			this.thread = thread;
		}


		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"T1");
		}

	}

	static class T2 extends Thread {
		Thread thread;
		

		public T2(Thread thread) {
			super();
			this.thread = thread;
		}
		@Override
		public void run() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"T2");
		}

	}

	static class T3 extends Thread {
		
		
		Thread thread;
		

		public T3(Thread thread) {
			super();
			this.thread = thread;
		}

		@Override
		public void run() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"T3");
		}

	}

	static class T4 extends Thread {

		Thread thread;
		

		public T4(Thread thread) {
			super();
			this.thread = thread;
		}
		
		@Override
		public void run() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"T4");
		}

	}
	
}
