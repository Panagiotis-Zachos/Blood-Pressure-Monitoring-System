package client_package;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Client {
	
	Socket socket;
	private BPBook book = new BPBook();
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private ClientData data;
	private Gson gson = new Gson();
	
	public Client() throws UnknownHostException, IOException, ClassNotFoundException {
		
		String server = "127.0.1";
		int servPort = 1234;
		socket = new Socket(server, servPort);
		
		dos = new DataOutputStream(socket.getOutputStream());
		dis = new DataInputStream(socket.getInputStream());

		System.out.println("Connected to server... ");

	}
		
	public String printBook() throws JsonSyntaxException, IOException {
		data = generateData(2);	
		String json = gson.toJson(data);
		dos.writeUTF(json);
		book = gson.fromJson(dis.readUTF(), BPBook.class);
		return book.toString();
	}
	
	public void storeBookLocally() throws JsonSyntaxException, IOException {
		data = generateData(4);	
		String json = gson.toJson(data);
		dos.writeUTF(json);
		book = gson.fromJson(dis.readUTF(), BPBook.class);
		book.storeBook();
	}
	
	public void sendFileToServer() throws IOException {
		data = generateData(5);	
		String json = gson.toJson(data);
		dos.writeUTF(json);
		book.readBookFromFile();
		dos.writeUTF(gson.toJson(book));
	}
	
	private ClientData generateData(Integer opt) {
		ClientData data;
		data = new ClientData(null, opt);		
		return data;
	}
	
//	private BPMeasurment getMeas() {
//		
//		Random rand = new Random();
//		Integer tmp = (rand.nextInt(1000000) + 1000000);	
//		String id = "up" + tmp.toString();
//		int sysBP = rand.nextInt(40) + 1;
//		int diasBP = rand.nextInt(70) + 1;
//		int heartRate = rand.nextInt(90) + 1;
//		
//		BPMeasurment meas = new BPMeasurment(id, sysBP, diasBP, heartRate);
//
//		return meas;
//	}
}
