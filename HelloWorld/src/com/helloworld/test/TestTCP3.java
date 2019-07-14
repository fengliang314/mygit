package com.helloworld.test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/*
 * 3.从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的连接。
 */
public class TestTCP3 {

	//客户端
	@Test
	public void client() throws IOException{
		Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 9898);
		
		FileInputStream fis = new FileInputStream("1.jpg");
		OutputStream os = s.getOutputStream();
		
		byte[] b = new byte[1024];
		int len = 0;
		while((len = fis.read(b)) != -1){
			os.write(b, 0, len);
		}
		
		s.shutdownOutput();
		
		//客户端接收反馈
		InputStream in = s.getInputStream();
		
		while((len = in.read(b)) != -1){
			System.out.println(new String(b, 0, len));
		}
		
		in.close();
		os.close();
		fis.close();
		s.close();
	}
	
	//服务端
	@Test
	public void server() throws IOException{
		ServerSocket ss = new ServerSocket(9898);
		Socket s = ss.accept();
		
		InputStream in = s.getInputStream();
		FileOutputStream fos = new FileOutputStream("2.jpg");
		
		byte[] b = new byte[1024];
		int len = 0;
		while((len = in.read(b)) != -1){
			fos.write(b, 0, len);
		}
		
		s.shutdownInput();
		
		OutputStream os = s.getOutputStream();
		
		os.write("发送成功".getBytes());
		
		os.close();
		fos.close();
		in.close();
		s.close();
		ss.close();
	}
	
}
