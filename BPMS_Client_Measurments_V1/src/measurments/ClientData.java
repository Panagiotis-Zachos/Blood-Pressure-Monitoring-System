package measurments;

import java.io.Serializable;

public class ClientData implements Serializable{
	
	private static final long serialVersionUID = -3047070490968883947L;
	public BPMeasurment meas;
	public Integer option;
	
	public ClientData(BPMeasurment meas, Integer option) {
		this.meas = meas;
		this.option = option;
	}
}
