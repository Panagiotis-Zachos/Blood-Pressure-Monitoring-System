package serv_package;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class CommandProcessor implements Runnable{

	private BPBook book = null;
	private ServerSocket servSock = null;
	private LinkedBlockingQueue<Socket> queue = null;
	
	public CommandProcessor(BPBook book2) {
		this.book = book2;
	}
	
	public void run(){
		try {
			
			this.start();
			while(true) {
				this.queue.put(this.servSock.accept());
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("Port already in use. Exiting...");
		}	
	}
	
	public void start() throws IOException {
		
		int servPort = 1234; 
		servSock = new ServerSocket(servPort);
		Thread[] thread_pool = new Thread[10];
		queue = new LinkedBlockingQueue<>();
		int i=0;
		
		while(i<10) {
			thread_pool[i] = new Thread(new Worker(book,queue),"Worker " + Integer.toString(i+1));
			thread_pool[i++].start();
		}
	}
}
