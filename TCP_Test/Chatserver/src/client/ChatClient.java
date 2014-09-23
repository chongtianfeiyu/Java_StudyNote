package client;

import java.awt.TextArea;
import java.awt.event.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.*;


public class ChatClient implements Runnable{
	public static void main(String[] args)  throws Exception{
		IPAddress = InetAddress.getByName("localhost");
		System.out.print("Please input your name:");
		while (true) {
			//@SuppressWarnings("resource")是用来压制资源泄露警告的，如使用IO类，最后没close
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String name = in.nextLine();
			if (name.split(" ").length > 1) {
				System.out.print("不能有空格!请重新输入：");
				continue;
			}
			user = new User(name);
			break;
		}
		clientSocket = new DatagramSocket();
		initialize();
		frame.setVisible(true);
		connect(user);
		ChatClient client = new ChatClient();
		new Thread(client).start();
	}
	
	@Override
	public void run() {
		while(true) {
			String recv;
			try {
				recv = recvFromServer();
				if (recv.equals("") || recv.equals(null))
					continue;
				String old = content.getText();
				content.setText(old + '\n' + recv);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// 发送关于新用户的信息
	public static void connect(User user) throws IOException {
		sendToServer("name " + user.getName());
	}
	
	public static void sendToServer(String sentence) throws IOException {
		sendData = sentence.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		clientSocket.send(sendPacket);
	}
	
	public static String recvFromServer() throws IOException {
		recvData = new byte[1024];
		recvPacket = new DatagramPacket(recvData, recvData.length);
		clientSocket.receive(recvPacket);
		return new String(recvPacket.getData());
	}
	
	private static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(12, 177, 338, 78);
		frame.getContentPane().add(textPane);
		/*
		 * 点击“发送”后，发送信息并清空发送窗口
		 */
		sendButton = new JButton("发送");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sentence = user.getName() + " say:\n    " + textPane.getText();
				textPane.setText("");
				try {
					sendToServer(sentence);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		sendButton.setBounds(362, 182, 60, 25);
		frame.getContentPane().add(sendButton);
		
		content = new TextArea();
		content.setBounds(12, 0, 420, 170);
		frame.getContentPane().add(content);

		//拓展用
		list = new JList<String>();
		list.setBounds(362, -1, 73, 170);
//		frame.getContentPane().add(list);
		
		button = new JButton("退出");
		button.setBounds(362, 219, 60, 25);
//		frame.getContentPane().add(button);
	}
	
	private static InetAddress IPAddress;
	private static int port = 8888;
	private static User user; 
	private static byte[] sendData;
	private static byte[] recvData;
	private static DatagramPacket sendPacket;
	private static DatagramPacket recvPacket;
	private static DatagramSocket clientSocket;
	private static JFrame frame;
	private static TextArea content;
	private static JButton button;
	private static JButton sendButton;
	private static JTextPane textPane;
	private static JList<String> list; 
}
