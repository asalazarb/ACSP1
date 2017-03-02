package imageProcessing;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


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
	public void ImagePixels(String input_image)
	{
		
		try {
	         InputStream input = new FileInputStream(input_image);
	         ImageInputStream imageInput = ImageIO.createImageInputStream(input);
	         BufferedImage imagenL = ImageIO.read(imageInput);
	         int width = imagenL.getWidth();
	         int height = imagenL.getHeight();
	         
	         int count = 0;
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               count++;
	               Color c = new Color(imagenL.getRGB(j, i));
	               System.out.println("Pixel #: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
	            }
	         }
	         
	      } catch (Exception e) {}
	}
	
	public void GrayScaleConversion(String input_image)
	{
		try {
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         File input = new File(input_image);
	         BufferedImage image = ImageIO.read(input);	

	         byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	         Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
	         mat.put(0, 0, data);

	         Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
	         Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

	         byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
	         mat1.get(0, 0, data1);
	         BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
	         image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

	         File ouptut = new File("grayscale.jpg");
	         ImageIO.write(image1, "jpg", ouptut);
	         
	      } catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
	      }
	}
}
