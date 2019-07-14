package com.helloworld.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

public class TestUDP {
	
	//发送端
	@Test
	public void send() throws IOException{
		String str = "使用 UDP 协议发送数据！";
		
		byte[] buf = str.getBytes();
		
		DatagramSocket ds = new DatagramSocket();
		
		int i = 0;
		while(i < 100){
			//每个数据包大小不超过 64 kb
			DatagramPacket dp = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName("127.0.0.1"), 9898);
			
			ds.send(dp);
			
			System.out.println("发送端发送了第" + i++ + "个数据");
		}
		

		
		ds.close();
	}
	
	//接收端
	@Test
	public void receive() throws IOException{
		DatagramSocket ds = new DatagramSocket(9898);
		
		byte[] buf = new byte[1024];
		
		int i = 0;
		
		while(i < 100){
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			
			ds.receive(dp);
			
			System.out.println(new String(dp.getData(), 0, dp.getLength()));
			
			System.out.println("接收端接收了第" + i++ + "个数据");
		}
		
		ds.close();
	}

}
