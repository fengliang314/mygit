package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
	int ticketNumber = 100;
	private Lock lock = new ReentrantLock();
	
	public void saleTicket(){
		lock.lock();
		
		try {
			if (ticketNumber > 0) {
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName() + "\t卖出：" + ticketNumber-- 
						+ "还剩" + ticketNumber);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}
}

public class SaleTicketThread {
	public static void main(String[] args) {
		
		final Ticket ticket = new Ticket();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 1; i <= 300; i++) {
					ticket.saleTicket();
				}
			}
		},"AA").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 1; i <= 300; i++) {
					ticket.saleTicket();
				}
			}
		},"BB").start();
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 1; i <= 300; i++) {
					ticket.saleTicket();
				}
			}
		},"CC").start();
	}
	
}
