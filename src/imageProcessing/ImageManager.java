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

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
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
	
	
	/**
	 * Carga una imagen y la transforma en matriz para transformarla en una imagen en escala de grises.
	 * no hay valor de retorno pero en el directorio del proyecto se crea la nueva imagen.
	 * En cada interación se recuperará el valor RGB. 
	 * @param  input_image Nombre/ruta del archivo.
	 */
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

	         File ouptut = new File("grayscale2.jpg");
	         ImageIO.write(image1, "jpg", ouptut);
	         
	      } catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
	      }
	}
	
	/**
	 * Este método utiliza un archivo de la carpeta de imágenes de prueba para generar un histograma.
	 */
	
	public void histogram(){
		
		try{
	        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	        File input = new File("testImages/celulas.png");
	        BufferedImage image = ImageIO.read(input);
		
	
	        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
	        mat.put(0, 0, data);
	
	        Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
	        Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
	        
	        ArrayList<Mat> imageList = new ArrayList<Mat>();
	        imageList.add(mat1);
	        MatOfInt histSize = new MatOfInt(256);


	        final MatOfFloat histRange = new MatOfFloat(0f, 256f);

	        boolean accumulate = false;

	        Mat hist = new  Mat();
	        
	       /* calcHist	(	const Mat * 	images,
	        		int 	nimages,
	        		const int * 	channels,
	        		InputArray 	mask,
	        		OutputArray 	hist,
	        		int 	dims,
	        		const int * 	histSize,
	        		const float ** 	ranges,
	        		bool 	uniform = true,
	        		bool 	accumulate = false 
	        		)	*/
	        Imgproc.calcHist(imageList, new MatOfInt(0),new Mat(), hist, histSize, histRange, accumulate);
	        
	        
	        Imgcodecs.imwrite("histogram.jpg", hist);
	       /* int hist_w = 512;
	        int hist_h = 600;
	        long bin_w;
	        bin_w = Math.round((double) (hist_w / 256));

	        Core.normalize(hist, hist, 3, hist.rows(), Core.NORM_MINMAX);



	         byte[] data1 = new byte[hist.rows() * hist.cols() * (int)(hist.elemSize())];
	         //hist.get(0, 0, data1);
	         BufferedImage image1 = new BufferedImage(hist.cols(),hist.rows(), BufferedImage.TYPE_BYTE_GRAY);
	         image1.getRaster().setDataElements(0, 0, hist.cols(), hist.rows(), data1);
	         
	         File ouptut = new File("histogram.jpg");
	         ImageIO.write(image1, "jpg", ouptut);*/

	        
		}
		
		catch (Exception e){
	        System.out.println("Error: " + e.getMessage());

		}
	}
}
