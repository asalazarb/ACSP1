package imageProcessing;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.HashMap;
import java.math.BigDecimal;

public class Image extends Graphic{

	private double t;
	private double average;
	private double sdeviation;
	private double p;
	private String name;
	BufferedImage image;
	public HashMap<Integer, Integer> histogram;
	public HashMap<Integer, Double> normHistogram;
	
	public Image(String pName, BufferedImage pImage){
		this.image = pImage;
		name = pName;
		this.histogram = new HashMap<Integer, Integer>();
		this.normHistogram = new HashMap<Integer, Double>();
		
	}
	
	public Image(){}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public HashMap<Integer, Integer> getHistogram() {
		return histogram;
	}

	public void setHistogram(HashMap<Integer, Integer> histogram) {
		this.histogram = histogram;
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
	
	
	public int getGrayValue(Color color){
		int grayValue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
		return grayValue;
	}
	
	public void generateHistogram(){
       
		for(int i=0; i<image.getHeight(); i++){
	         
            for(int j=0; j<image.getWidth(); j++){
            
               Color color = new Color(image.getRGB(j, i));
               int grayValue = getGrayValue(color);
               
               
               if(histogram.containsKey(grayValue)){
            	   int value = histogram.get(grayValue);
            	   value++;
            	   histogram.put(grayValue, value);
               }
               
               else{
            	   histogram.put(grayValue, 0);
               }
            }
         }
		normalizeHistogram();
	}
	private void normalizeHistogram(){
	
		int n = 0;
		String stringDecimal;
		
		
		for(int value : histogram.values()){
			n += value;
		}
		
		for(int key : histogram.keySet()){
			int keyVal = histogram.get(key);
			double newVal = keyVal / (float)n;
			
			stringDecimal = String.format("%.7g%n", newVal);
			System.out.println(stringDecimal);
			if(stringDecimal.toLowerCase().contains("e")){
				newVal = 0.0;
			}
			
			else{
				newVal = Double.parseDouble(stringDecimal.replaceAll(",", "."));
			}
		
			normHistogram.put(key, newVal);			
		}
		
		
	}
}
