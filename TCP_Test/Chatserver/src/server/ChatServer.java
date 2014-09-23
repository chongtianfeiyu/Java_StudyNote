package server;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class ChatServer extends Thread{

	public static void main(String[] args) throws Exception {
/* Example:
 * <code>
 *              DatagramSocket s = new DatagramSocket(null);
 *              s.bind(new InetSocketAddress(8888));
 * </code>
 * Which is equivalent to:
 * <code>
 *              DatagramSocket s = new DatagramSocket(8888);
 * </code>
 */
		serverSocket = new DatagramSocket(8888);
		while (true) {
			sentence = recvFromClient();
			// 若是从client发来的关于user的报文，接收但不转发
			if (sentence.split(" ")[0].equals("name"))
				continue;
			// 主线程接收报文，每接收到一个报文就创建一个线程用于转发报文，能减少因转发报文而要等待的时间
			new ChatServer().start();
		}
	}
	
	/*
	 * 对每个IP转发接收到报文
	 */
	public static void sendToClient(String sentence) throws IOException {
        sendData = sentence.getBytes();
        portSet = ipMap.keySet();
		Iterator<Integer> i = portSet.iterator();
		while (i.hasNext()) {
			Integer port = i.next();
			IPAddress = ipMap.get(port);
			sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}

	@Override
	public void run(){
		try {
			if (sentence.equals("") || sentence.equals(null))
				return;
			sendToClient(sentence);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 从客户端接收UDP报文，并用ipMap记录port 和 IP。
	 * 返回：聊天信息
	 */
	public static String recvFromClient() throws IOException {
		recvData = new byte[1024];
		recvPacket = new DatagramPacket(recvData, recvData.length);
		serverSocket.receive(recvPacket);
		IPAddress = recvPacket.getAddress();
		int port = recvPacket.getPort();
		ipMap.put(port, IPAddress);
		return new String(recvPacket.getData());
	}
	
	private static byte[] recvData;
	private static byte[] sendData;
	private static DatagramPacket sendPacket;
	private static DatagramPacket recvPacket;
	private static DatagramSocket serverSocket;
	private static InetAddress IPAddress;
	private static Set<Integer> portSet;
	private static Map<Integer, InetAddress> ipMap = new HashMap<>();
	private static String sentence;
	
}
