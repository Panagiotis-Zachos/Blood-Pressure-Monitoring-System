package client_package;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientMain {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		
		Client client = new Client();
		ClientGui gui = new ClientGui(client);
	}

}
