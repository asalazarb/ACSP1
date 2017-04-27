package imageProcessing;

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
         
		saveImage(buffImage);
	}
	
	public void saveImage(BufferedImage image){
		 
		File output = new File("testImages/saved.jpg");
		BufferedImage image1 = new BufferedImage(512,512, BufferedImage.TYPE_BYTE_GRAY);
	    try {
			ImageIO.write(image, "jpg", output);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
