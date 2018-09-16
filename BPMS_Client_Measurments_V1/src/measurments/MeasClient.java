package measurments;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;


public class MeasClient {

	Socket socket;
	private DataOutputStream dos = null;
	private ClientData data;
	private Gson gson = new Gson();
	
	public MeasClient() throws UnknownHostException, IOException {
		
		try {
			readIPFromFile();
			dos = new DataOutputStream(socket.getOutputStream());
			
			sendMeasurment();
			dos.writeUTF(gson.toJson(generateData(6)));
		} catch(ConnectException e1) {
			System.out.println("The server is probably not running.");
		} catch(UnknownHostException e2) {
			System.out.println("Wrong IP Address.");
		}
		
	}
	
	public void sendMeasurment() throws IOException {
		data = generateData(1);	
		String json = gson.toJson(data);
		dos.writeUTF(json);
	}
	
	private ClientData generateData(Integer opt) {
		
		ClientData data;
		
		switch(opt) {
		case 1:
			BPMeasurment meas = getMeas();
			data = new ClientData(meas, opt);
			break;
			
		default:
			data = new ClientData(null, opt);
		}
		
		return data;
	}
	
	private BPMeasurment getMeas() {
		
		Random rand = new Random();
		Integer tmp = (rand.nextInt(1000000) + 1000000);	
		String id = "up" + tmp.toString();
		int sysBP = rand.nextInt(40) + 1;
		int diasBP = rand.nextInt(70) + 1;
		int heartRate = rand.nextInt(90) + 1;
		
		BPMeasurment meas = new BPMeasurment(id, sysBP, diasBP, heartRate);

		return meas;
	}
	
	private void readIPFromFile() throws IOException {
		
		Scanner sc = new Scanner(new File("IP_Address.txt")); 
	    sc.useDelimiter("\\Z"); 
	    String server = sc.next().trim();
		int servPort = 1234;
		socket = new Socket(server, servPort);
		sc.close();
	}
	
}
