package serv_package;

import java.io.IOException;
import java.text.ParseException;

public class ServerMain {
	
	public static void main(String[] args) throws IOException, ParseException {
		
		BPBook book = new BPBook();
		Thread t2 = new Thread(new CommandProcessor(book),"CommandProcessor");
		t2.start();
	}

}
