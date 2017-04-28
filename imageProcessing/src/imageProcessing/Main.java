package imageProcessing;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ImageManager controlador = new ImageManager();
		
		//controlador.ImagePixels("testImages/celulas.png");
		Image i = controlador.createImage("testImages/celulas.png");
		Kittler k = new Kittler();
		k.kittler(i);
		ImageTagger it = new ImageTagger();
		
		it.segmentImage(i);
		
		System.out.println(i.getT());
	}

}
