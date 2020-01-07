package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

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
		Socket s;
		try {
			s = new Socket(ip, port);
			DataOutputStream dO = new DataOutputStream(s.getOutputStream());
			dO.writeUTF("Hi server!");

			DataInputStream dI = new DataInputStream(s.getInputStream());
			System.out.println(dI.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
