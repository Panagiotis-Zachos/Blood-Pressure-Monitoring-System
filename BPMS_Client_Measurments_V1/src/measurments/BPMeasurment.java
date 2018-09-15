package measurments;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class BPMeasurment implements Serializable{
	
	private static final long serialVersionUID = -742245342132947035L;
	public Date dt;
	public String date;
	public String time;
	public String userID;
	public int systolicBP;
	public int diastolicBP;
	public int heartRate;
	private static final SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat f2 = new SimpleDateFormat("hh:mm:ss a");
	
	public BPMeasurment(String userID, int systolicBP, int diastolicBP, int heartRate) {
		
		this.dt = new Date();
		this.date = f1.format(dt);
		this.time = f2.format(dt);
		this.userID = userID;
		this.systolicBP = systolicBP;
		this.diastolicBP = diastolicBP;
		this.heartRate = heartRate;
	}
	
	public BPMeasurment(Date date, String userID, int systolicBP, int diastolicBP, int heartRate) {	
		
		this(userID, systolicBP, diastolicBP, heartRate);
		this.dt = date;
	}
	
	static class dateComparator implements Comparator<BPMeasurment>{	// Descending
		public int compare(BPMeasurment o1, BPMeasurment o2) {
			return o2.dt.compareTo(o1.dt);
		}
	}
	
	public static BPMeasurment lineToMeasurment(String line) throws ParseException {
		
		String measurment_data[];
		Date dt;
		String userID;
		int systolicBP;
		int diastolicBP;
		int heartRate;
		
		SimpleDateFormat f3 = new SimpleDateFormat("dd/MM/yyyyhh:mm:ss a");
		
		measurment_data = line.split("\t");
		dt = f3.parse(measurment_data[0].concat(measurment_data[1]));
		userID = measurment_data[2];
		systolicBP = Integer.parseInt(measurment_data[3]);
		diastolicBP = Integer.parseInt(measurment_data[4]);
		heartRate = Integer.parseInt(measurment_data[5].trim());	//Needed because BUFSIZE in Server.java is set to 64 resulting in trailing whitespaces
		
		BPMeasurment meas = new BPMeasurment(dt, userID, systolicBP, diastolicBP, heartRate);
		System.out.println(meas);
		return meas;
	}
	
	@Override
	public String toString() {
		return this.date + "\t" + this.time + "\t" + this.userID + "\t" + this.systolicBP + "\t" + this.diastolicBP + "\t" + this.heartRate;
	}
}
