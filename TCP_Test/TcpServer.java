package zyh.tcp;

import java.io.*;
import java.net.*;

class TcpServer extends Thread{
	public TcpServer() throws IOException {
		String clientSentence;
		String acSentence;
		ServerSocket wel = new ServerSocket(6789);
		Socket connection = wel.accept();

		while (true) {
			System.out.println("I'm waiting...");
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
				connection.getOutputStream());
			clientSentence = inFromClient.readLine();
			if (clientSentence == null)
				break;
			acSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(acSentence);
		}
		wel.close();
	}
	public static void main(String argv[]) throws Exception
	{
		//while (true) {
			new TcpServer().start();
		//}
	}
}
