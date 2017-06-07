
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTagger implements GraphicHandler{

	@Override
	public Graphic open(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void iterate() {
		// TODO Auto-generated method stub
		
	}
	
	public void segmentImage(Image image){
		BufferedImage buffImage = image.getImage();
		int w = buffImage.getWidth();
		int h = buffImage.getHeight();
		double t = image.getT();
		int grayValue;
		
		
		for(int i=0; i<buffImage.getHeight(); i++){
	         
            for(int j=0; j<buffImage.getWidth(); j++){
            
               Color color = new Color(buffImage.getRGB(j, i));
	            grayValue = image.getGrayValue(color);
				if(grayValue > t){
					Color newColor = new Color(255,255,255);
					buffImage.setRGB(j, i, newColor.getRGB());
				}
				
				else{
					Color newColor = new Color(0,0,0);
					buffImage.setRGB(j, i, newColor.getRGB());
				}
			}
        }
		BufferedImage colorImage = new BufferedImage(buffImage.getWidth(), buffImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        colorImage.getGraphics().drawImage(buffImage, 0, 0, null);
		image.setImage(colorImage);
        tagImage(image);
		saveImage(colorImage);
	}
	
	private boolean pixel(Image image, int x, int y, int w, int h){
		
		if(x >= 0 && y >= 0 && x < w && y < h){
			Color color = new Color(image.getImage().getRGB(x, y));
			if(image.getGrayValue(color) == 255){
				return true;
			}
		}

		return false;
	}
	
	private boolean nextPixels(Image image, int x, int y, int w, int h){
		
		BufferedImage buffImage = image.getImage();
		
		int N = y + 1;
		int S = y - 1;
		int E = x + 1;
		int W = x - 1;
		
		if(pixel(image, x, N, w, h)){
			return true;
		}
		
		if(pixel(image, x, S, w, h)){
			return true;
		}
		if(pixel(image, E, y, w, h)){
			return true;
		}
		if(pixel(image, W, y, w, h)){
			return true;
		}
		if(pixel(image, N, E, w, h)){
			return true;
		}
		if(pixel(image, N, W, w, h)){
			return true;
		}
		if(pixel(image, S, E, w, h)){
			return true;
		}
		if(pixel(image, S, W, w, h)){
			return true;
		}
		
		return false;
		
	}
	
	public void tagImage(Image image){
		BufferedImage buffImage = image.getImage();
		int w = buffImage.getWidth();
		int h = buffImage.getHeight();
		int grayValue;
		
		
		for(int i=0; i<buffImage.getHeight(); i++){
	         
            for(int j=0; j<buffImage.getWidth(); j++){
            
               Color color = new Color(buffImage.getRGB(j, i));
	            grayValue = image.getGrayValue(color);
				if(grayValue == 0){
					if(nextPixels(image, j, i, w, h)){
						System.out.println("color");
					
						buffImage.setRGB(j, i, Color.GREEN.getRGB());
					}
				}
				
			}
        }
	}
	
	public void saveImage(BufferedImage image){
		System.out.println("Guardado");
		File output = new File("C:/Users/pc/Desktop/Andrés/ACSP1/Proyecto1/resultados/saved4.jpg");
		
		
	    try {
			ImageIO.write(image, "jpg", output);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
