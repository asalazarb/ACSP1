package imageProcessing;
import java.util.HashMap;

public class Kittler implements Algorithm{
	
	@Override
	public Image run(){
		Image image = new Image();
		return image;
	}
	
	public void kittler(Image image){
		double minJ = 999999.99;
		for(int t=0; t < 256; t++){
			double temp = J(t, image);
			if(temp < minJ){
				minJ = temp;
			}
		}
		image.setT(minJ);
	}
	
	private double J(int t, Image image){
		KittlerResult k1 = calcParameters(t, false, image.normHistogram);
		KittlerResult k2 = calcParameters(t, true, image.normHistogram);
		
		double Jt = 1 + 2*(k1.getP() * Math.log(k1.getSdeviation()) + k2.getP() * Math.log(k2.getSdeviation())) 
				      - 2*(k1.getP() * Math.log(k1.getP()) + k2.getP() * Math.log(k2.getP())); 
				
	    return Jt;
	}
	

	
	private KittlerResult calcParameters(int t, boolean flag, HashMap<Integer, Double> histogram){
		int a = 0;
		int b = t + 1;
		
		if(flag){
			a = t;
			b = 255;
		}
		
		double p = 0;
		double average = 0;
		double sDeviation = 0;

		for(int z=a; z<b; z++){
			p = p + histogram.get(t);
			average += histogram.get(z) * z;
		}
		
		average = average / p;
		
		for(int z=a; z<b; z++){
			sDeviation += histogram.get(z) * Math.pow((z - average), 2);
		}
		
		KittlerResult parameters = new KittlerResult(p, average, (sDeviation / p));
		return parameters;
	}
	

	
	

}
