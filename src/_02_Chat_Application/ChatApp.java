package _02_Chat_Application;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatApp extends Thread {
	ServerSocket s;

	public ChatApp() throws IOException {
		s = new ServerSocket(8090, 20);
		s.setSoTimeout(100000);
	}

	public void run() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton send = new JButton();
		JTextField field = new JTextField(30);
		Label text = new Label();
		frame.add(panel);
		panel.add(text);
		panel.add(field);
		panel.add(send);
		send.setText("SEND");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(400,400);
		text.setSize(350,350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boolean var = true;
		System.out.println("What is your name?");
		String name = new Scanner(System.in).nextLine();
		while (var) {
			try {
				System.out.println("AWAITING CONNECTION");
				Socket sock = s.accept();
				System.out.println("CONNECTION ESTABLISHED");

				DataInputStream di = new DataInputStream(sock.getInputStream());

				DataOutputStream dO = new DataOutputStream(sock.getOutputStream());
				
				Scanner scan = new Scanner(System.in);
				String thing = name + ": " + new Scanner(System.in).nextLine();
				send.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							dO.writeUTF(field.getText());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						text.setText(text.getText()+field.getText()+"\n");
						field.setText("");
					}
					
				});

				System.out.println(thing);

			} catch (SocketTimeoutException e) {
				e.printStackTrace();
				var = false;
			} catch (IOException e1) {
				e1.printStackTrace();
				var = false;
			}
		}
	}

	public static void main(String[] args) {
		try {
			new ChatApp().run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
