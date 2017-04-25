package imageProcessing;

import java.util.HashMap;;

public class Image extends Graphic{

	private double t;
	private double average;
	private double sdeviation;
	private String name;
	public HashMap<Integer, Integer> histogram;
	
	
	
	public Image(String pName){
		name = pName;
		histogram = new HashMap<Integer, Integer>();
	}
	
	public double getT() {
		return t;
	}
	public void setT(double t) {
		this.t = t;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public double getSdeviation() {
		return sdeviation;
	}
	public void setSdeviation(double sdeviation) {
		this.sdeviation = sdeviation;
	}
	
}
