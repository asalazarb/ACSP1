import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.Color;

public class csvHandler {
	public HashMap<Integer, String> colors;
	
	public csvHandler(){
		this.colors = new HashMap<Integer, String>();
		colors.put(Color.BLUE.getRGB(), "BLUE");
		colors.put(Color.CYAN.getRGB(), "CYAN");
		colors.put(Color.GREEN.getRGB(), "GREEN");
		colors.put(Color.ORANGE.getRGB(), "ORANGE");
		colors.put(Color.RED.getRGB(), "RED");
		colors.put(Color.YELLOW.getRGB(), "YELLOW");
	}
	
	public void generateData(int[][] pixels, int w, int h, LinkedList<Integer> orderedColors, String name){
		
		HashMap<Integer, Integer> labelValues = new HashMap<Integer, Integer>();
		
		for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
               
               if(labelValues.containsKey(pixels[i][j])){
            	   int value = labelValues.get(pixels[i][j]);
            	   value++;
            	   labelValues.put(pixels[i][j], value);
               }
               
               else{
            	   labelValues.put(pixels[i][j], 0);
               }
            }
         }
		export(labelValues, orderedColors, name);
	}
	
	private void export(HashMap<Integer, Integer> labelValues, LinkedList<Integer> orderedColors, String name){
		String content = "";
		String path = "C:/Users/pc/Desktop/Andrés/ACSP1/Proyecto1/csv/" + name +".csv";
		
		int counter = 0;
		for(int key : labelValues.keySet()){
			int area = labelValues.get(key);
			String color = colors.get(orderedColors.get(counter));
			content += key + "," + area + "," + color + "\n";
			counter++;
		}
		
		try {
			Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
