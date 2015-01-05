package com.zml.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zml.thread.Socket_thread;


public class Socket_server {

	private static ExecutorService executoService = null;//�����̳߳�
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try { 
			executoService = Executors.newCachedThreadPool();//�����̳߳�
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ServerSocket serversocket = new ServerSocket();
			serversocket.setReuseAddress(true);
			SocketAddress endpoint = new InetSocketAddress(3333);
			serversocket.bind(endpoint, 10);
			while(true) {
				System.out.println("�ȴ��ͻ�������........");
				Socket client = serversocket.accept();
				executoService.execute(new Socket_thread(client));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
