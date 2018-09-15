package serv_package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BPBook{
	
	private List<BPMeasurment> BPList = new ArrayList<BPMeasurment>();
	
	public void addMeasurment(String userID, int sysBP, int diasBP, int heartRate) {
		BPMeasurment meas = new BPMeasurment(userID, sysBP, diasBP, heartRate);
		this.BPList.add(meas);
	}
	
	public void addMeasurment(BPMeasurment meas) {
		this.BPList.add(meas);
	}
	
	public void printBook() {
		System.out.println("Date\t\tTime\t\tUserID\t\tSystolicBP\tDiastolicBP\tHeart Rate");
		for(BPMeasurment meas : BPList) {
			System.out.println(meas);
		}
	}
	
	public void sortBookbyDate() {	// Descending Order
		Collections.sort(BPList, new BPMeasurment.dateComparator());
	}
	
	public void storeBook() throws FileNotFoundException {		// TEMPORARY. Should take String FileLoc as argument. 
		PrintWriter out = new PrintWriter("BPBook.txt");
		for(BPMeasurment meas : BPList) {
			out.println(meas);
		}
		out.close();
	}
	
	public void readBookFromFile() throws IOException {			// TEMPORARY. Should take String FileLoc as argument. 
		BPList.clear();
		File file = new File("BPBook.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		 
		String st;
				  
		while ((st = br.readLine()) != null){
			try {
				BPList.add(BPMeasurment.lineToMeasurment(st));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		br.close();
	}
	
	public void addFromBPBook(BPBook book) {
		for(BPMeasurment meas : book.BPList) {
			this.addMeasurment(meas);
		}
	}
	
	public void getFromStdIn() throws ParseException {
		Scanner in = new Scanner(System.in);
		String  userID;
		int sysBP, diasBP, heartRate;
		
		userID = in.nextLine();
		sysBP = in.nextInt();
		diasBP = in.nextInt();
		heartRate = in.nextInt();
		addMeasurment(userID, sysBP, diasBP, heartRate);
		
		in.close();
	}
	
	@Override
	public String toString() {
		String book = "";
		for (BPMeasurment meas : this.BPList) {
			book = book + meas.toString() + "\n";
		}
		return book;
	}
	
}
