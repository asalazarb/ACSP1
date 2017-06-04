import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ImageManager controlador = new ImageManager();
		
		//controlador.ImagePixels("testImages/celulas.png");
		Image i = controlador.createImage("testImages/img1.png");
		Kittler k = new Kittler();
		k.kittler(i);
		/*ImageTagger it = new ImageTagger();
		
		it.segmentImage(i);
		
		System.out.println(i.getT());*/
		
		System.out.println(i.getT());
		
		
		
		
		
		
		/*for(int key : i.histogram.keySet()){
			int keyVal = i.histogram.get(key);
			double newVal = keyVal / (float)n;
			
			System.out.println(newVal);
		}*/
		
		
	}

}