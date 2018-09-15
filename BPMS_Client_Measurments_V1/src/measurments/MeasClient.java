package measurments;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import com.google.gson.Gson;


public class MeasClient {

	Socket socket;
	private DataOutputStream dos = null;
	private ClientData data;
	private Gson gson = new Gson();
	
	public MeasClient() throws UnknownHostException, IOException {
		String server = "127.0.0.1";
		int servPort = 1234;
		socket = new Socket(server, servPort);
		
		dos = new DataOutputStream(socket.getOutputStream());
		
		sendMeasurment();
		dos.writeUTF(gson.toJson(generateData(6)));
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
	
}
