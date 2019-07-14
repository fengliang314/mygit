package com.helloworld.test;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/*
 * 2.客户端发送内容给服务端，服务端给予反馈。
 */
public class TestTCP2 {
	
	//客户端
	@Test
	public void client(){
		String str = "客户端发送数据给服务端！";
		
		Socket s = null;
		OutputStream os = null;
		InputStream in = null;
		try {
			s = new Socket(InetAddress.getByName("127.0.0.1"), 9898);
			
			os = s.getOutputStream();
			
			os.write(str.getBytes());
			
			s.shutdownOutput();

			//客户端接收服务端的反馈
			in = s.getInputStream();
			
			byte[] b = new byte[1024];
			int len = 0;
			while((len = in.read(b)) != -1){
				System.out.println(new String(b, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(s != null){
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//服务端
	@Test
	public void server() throws IOException{ //你们必须 try
		ServerSocket ss =  new ServerSocket(9898);
		Socket s = ss.accept();
		
		InputStream in = s.getInputStream();
		
		byte[] b = new byte[1024];
		int len = 0;
		while((len = in.read(b)) != -1){
			System.out.println(new String(b, 0, len));
		}
		
		s.shutdownInput();
		
		//发送反馈给客户端
		OutputStream os = s.getOutputStream();
		
		os.write("接收成功".getBytes());
		
		os.close();
		in.close();
		s.close();
		ss.close();
	}

}
