package serv_package;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.gson.Gson;

public class Worker implements Runnable{
	
	private BPBook book;
	private BPMeasurment meas;
	private Socket clntSock;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private LinkedBlockingQueue<Socket> socketQueue;
	
	public Worker(BPBook book, LinkedBlockingQueue<Socket> queue) throws IOException {
		this.book = book;
		this.socketQueue = queue;
	}
	
	public void run() {
		
		try {
			Gson gson = new Gson();
			acceptNewConnection();
			for(;;) {
				try {
					
				    ClientData data = gson.fromJson(dis.readUTF(), ClientData.class) ;
				    
				    switch(data.option) {
				    case 1:
				    	meas = data.meas;
				    	book.addMeasurment(meas);
				    	book.printBook();
				    	break;
				    case 2:
				    case 4:
				    	System.out.println("Sending measurments to client...");
				    	dos.writeUTF(gson.toJson(book));
				    	System.out.println("Measurments Sent");
				    	break;
				    case 3:
				    	System.out.println("Sorting not implemented yet.");
				    	break;
				    case 5:
				    	System.out.println("Waiting client's measurments...");
				    	BPBook temp_book = gson.fromJson(dis.readUTF(), BPBook.class);
				    	System.out.println("Measurments received. Printing...\n");
				    	temp_book.printBook();
				    	System.out.println("Storing measurments...");
				    	book.addFromBPBook(temp_book);
				    	book.storeBook();
				    	break;
				    case 6:
				    	clntSock.close();
				    	acceptNewConnection();
				    	break;
				    }					
				}
				catch (java.io.EOFException e){
					continue;
				}
				catch (java.net.SocketException e) {
					System.out.println("Client terminated connection.");
					clntSock.close();
			    	acceptNewConnection();
			    	continue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void acceptNewConnection(){
		try {
			this.clntSock = socketQueue.take();
			this.dis = new DataInputStream(clntSock.getInputStream());
			this.dos = new DataOutputStream(clntSock.getOutputStream());
			System.out.println("\nHandling client at " + clntSock.getInetAddress().getHostAddress() + " on port " + clntSock.getPort() + "\n");
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
