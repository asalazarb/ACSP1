
public class KittlerResult {
	private double p;
	private double average;
	private double sdeviation;
	

	
	public KittlerResult(double p, double average, double sdeviation) {
		this.p = p;
		this.average = average;
		this.sdeviation = sdeviation;
	}
	public double getP() {
		return p;
	}
	public void setT(double t) {
		this.p = p;
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