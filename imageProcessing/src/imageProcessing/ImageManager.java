package imageProcessing;


import java.awt.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.color.*;




public class ImageManager implements GraphicHandler{

	@Override
	public Graphic open(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void iterate() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Carga una imagen y la transforma en matriz para luego recorrerla pixel por pixel.
	 * En cada interación se recupera el valor RGB. 
	 * @param  input_image Nombre/ruta del archivo.
	 */
	public void ImagePixels(String input_image)
	{
		
		try {
	         InputStream input = new FileInputStream(input_image);
	         ImageInputStream imageInput = ImageIO.createImageInputStream(input);
	         BufferedImage imagenL = ImageIO.read(imageInput);
	         int width = imagenL.getWidth();
	         int height = imagenL.getHeight();
	         
	         
	         int[][] buffer = new int [width][height];
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               buffer[i][j] = imagenL.getRGB( i, j );
	               
	            }
	         }
	         
	      } catch (Exception e) {}
	}
	
	
	/**
	 * Carga una imagen y la transforma en matriz para transformarla en una imagen en escala de grises.
	 * no hay valor de retorno pero en el directorio del proyecto se crea la nueva imagen.
	 * En cada interación se recuperará el valor RGB. 
	 * @param  input_image Nombre/ruta del archivo.
	 */
	public Image GrayScaleConversion(String input_image)
	{
		try {
	         //System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         File input = new File(input_image);
	         BufferedImage image = ImageIO.read(input);	

	        
	         BufferedImage image1 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	         image1.getGraphics().drawImage(image, 0, 0, null);

	         
	         Image i = new Image(input_image,image1);
	         
	         
	         return i;
	      } catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
	      }
		return null;
	}
	

	public Image createImage(String input_image){
		Image newImage = GrayScaleConversion(input_image);
		newImage.generateHistogram();
		//agregar a lista
		return newImage;
	}

}
