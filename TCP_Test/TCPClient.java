import java.io.*;
import java.net.*;

class TCPClient {
	public static void main(String[] args) throws Exception{
		String sentence = "";
		BufferedReader inFromUser = new BufferedReader(
			new InputStreamReader(System.in));
		Socket client = new Socket("localhost", 6789);
		DataOutputStream outToServer = new DataOutputStream(
			client.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(
			new InputStreamReader(client.getInputStream()));

		while (!sentence.equalsIgnoreCase("exit")) {
			sentence = inFromUser.readLine();
			outToServer.writeBytes(sentence + '\n');
			sentence = inFromServer.readLine();
			System.out.println("From server: " + sentence);
		}
		System.out.println("testing!");
		client.close();
	}
}
