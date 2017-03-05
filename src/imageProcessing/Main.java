package imageProcessing;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ImageManager controlador = new ImageManager();
		
		controlador.ImagePixels("testImages/celulas.png");
		//controlador.GrayScaleConversion("testImages/celulas.png");
		System.out.println("terminado");
	}

}
