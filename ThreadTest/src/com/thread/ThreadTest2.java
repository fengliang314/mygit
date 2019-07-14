package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 101010

class ShareData2{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void add(){
		lock.lock();
		try {
			while (number != 0) {
				//this.wait();
				condition.await();
			}
			++number;
			System.out.println(Thread.currentThread().getName()+":\t" + number);
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//this.notifyAll
			lock.unlock();
		}
		
	}
	
	public void reduce(){
		lock.lock();
		try {
			while (number == 0) {
				//this.wait();
				condition.await();
			}
			--number;
			System.out.println(Thread.currentThread().getName()+":\t" + number);
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//this.notifyAll();
			lock.unlock();
		}
	}
	
}

public class ThreadTest2 {
	public static void main(String[] args) {
		final ShareData2 sd = new ShareData2();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i <= 10 ; i++) {
					try {
						Thread.sleep(200);
						sd.add();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		},"aa").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i <= 10 ; i++) {
					try {
						Thread.sleep(300);
						sd.reduce();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		},"bb").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i <= 10 ; i++) {
					try {
						Thread.sleep(400);
						sd.add();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		},"cc").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i <= 10 ; i++) {
					try {
						Thread.sleep(500);
						sd.reduce();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		},"dd").start();
	}
}
