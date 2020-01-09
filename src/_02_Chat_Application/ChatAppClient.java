package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatAppClient {
	public static void main(String[] args) {
		String ip = "localhost";
		
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(ip);
		int port = 8090;
		Socket s=null;
		try {
			s = new Socket(ip, port);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("CONNECTION ESTABLISHED");
		System.out.println("Enter your name or you are lame");
		String name = new Scanner(System.in).nextLine();
		while(s.isConnected()) {
		try {
			DataOutputStream dO = new DataOutputStream(s.getOutputStream());
			String msg = name + ": "+ new Scanner(System.in).nextLine();
			dO.writeUTF(msg);
			DataInputStream dI = new DataInputStream(s.getInputStream());
			System.out.println(dI.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
}
