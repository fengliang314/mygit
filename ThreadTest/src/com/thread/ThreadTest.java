package com.thread;

// 101010

class ShareData{
	private int number = 0;
	
	public synchronized void add(){

		try {
			if (number != 0) {
				this.wait();
			}
			++number;
			System.out.println(Thread.currentThread().getName()+":\t" + number);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			this.notifyAll();
		}
		
	}
	
	public synchronized void reduce(){
		try {
			if (number == 0) {
				this.wait();
			}
			--number;
			System.out.println(Thread.currentThread().getName()+":\t" + number);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			this.notifyAll();
		}
	}
	
}

public class ThreadTest {
	public static void main(String[] args) {
		final ShareData sd = new ShareData();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i <= 10 ; i++) {
					sd.add();
				}
				
			}
		},"aa").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i <= 10 ; i++) {
					sd.reduce();
				}
			}
		},"bb").start();
	}
}
