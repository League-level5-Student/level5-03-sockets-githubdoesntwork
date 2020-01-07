package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends Thread{
	ServerSocket s;

	public ChatApp() throws IOException {
		s = new ServerSocket(8090,100);
		s.setSoTimeout(100000);
	}

	public void run() {
		boolean var = true;
		System.out.println("What is your name?");
		String name = new Scanner(System.in).nextLine();
		while (var) {
			try {
				System.out.println("server waiting");
				Socket sock = s.accept();
				System.out.println("client connected");
				DataInputStream di = new DataInputStream(sock.getInputStream());
				System.out.println(di.readUTF());
				DataOutputStream dO = new DataOutputStream(sock.getOutputStream());
				Scanner scan = new Scanner(System.in);
				String thing = "_____________________________\n";
				 thing += scan.nextLine();
				thing+="\n_____________________________";
				thing+="    /";
				thing+="   /";
				thing+="  /";
				thing+=name+":";
				dO.writeUTF(thing);
				System.out.println(thing);
			} catch (SocketTimeoutException e) {
				e.printStackTrace();
				var=false;
			} catch (IOException e1) {
				e1.printStackTrace();
				var=false;
			}
		}
	}
	public static void main(String[] args) {
		try {
			Thread t = new Thread(new ChatApp());
			t.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
