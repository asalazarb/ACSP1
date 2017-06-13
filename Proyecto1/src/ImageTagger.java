
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTagger implements GraphicHandler{
	
	public static int[] colors = {Color.BLUE.getRGB(), Color.CYAN.getRGB(), Color.GREEN.getRGB(), Color.ORANGE.getRGB(),
								  Color.RED.getRGB(), Color.YELLOW.getRGB()};
	public static int numberColors = 6;
	public static int procImages = 0;

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
		
        image.setImage(buffImage);
        int [][] matrix = labelImage(image);
        image.setImage(colorImage);
		
		LinkedList<Integer> usedColors = tagImage(image);
		String fileName = saveImage(image.getImage());
		csvHandler csvH = new csvHandler();
		csvH.generateData(matrix, colorImage.getWidth(), colorImage.getHeight(), usedColors, fileName);
		
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

		
		return false;
		
	}
	
	public LinkedList<Integer> tagImage(Image image){
		BufferedImage buffImage = image.getImage();
		int w = buffImage.getWidth();
		int h = buffImage.getHeight();
		int grayValue;
		LinkedList<Integer> usedColors = new LinkedList<Integer>();
		
		for(int i=0; i<buffImage.getHeight(); i++){
	         
            for(int j=0; j<buffImage.getWidth(); j++){
            
               Color color = new Color(buffImage.getRGB(j, i));
	            grayValue = image.getGrayValue(color);
				if(grayValue == 0){
					if(nextPixels(image, j, i, w, h)){
						int newColor = 0 + (int)(Math.random() * (((this.numberColors-1) - 0) + 1));
						usedColors.add(colors[newColor]);
						buffImage.setRGB(j, i, colors[newColor]);
					}
				}
				
			}
        }
		return usedColors;
	}
	
	private void checkQueue(int [][]pixels, Image image, Queue<Pixel> queue, int label){
		if(queue.size() == 0){
			return;
		}
		
		BufferedImage buffImage = image.getImage();
		int w = buffImage.getWidth();
		int h = buffImage.getHeight();
		int grayValue;
		
		Pixel pixel = queue.remove();
		int N = pixel.y + 1;
		int S = pixel.y - 1;
		int E = pixel.x - 1;
		int W = pixel.x + 1;
		
		if(pixel.x >= 0 && N >= 0 && pixel.x < w && N < h){
			Color color = new Color(buffImage.getRGB(pixel.x, N));
            grayValue = image.getGrayValue(color);
			if(grayValue == 255 && pixels[pixel.x][N] == 0){
				pixels[pixel.x][N] = label;
				Pixel neighbor = new Pixel(pixel.x, N);
				queue.add(neighbor);
				checkQueue(pixels, image, queue, label);
			}
		}
		if(pixel.x >= 0 && S >= 0 && pixel.x < w && S < h){
			Color color = new Color(buffImage.getRGB(pixel.x, S));
            grayValue = image.getGrayValue(color);
			if(grayValue == 255 && pixels[pixel.x][S] == 0){
				pixels[pixel.x][S] = label;
				Pixel neighbor = new Pixel(pixel.x, S);
				queue.add(neighbor);
				checkQueue(pixels, image, queue, label);
			}
			
		}
		if(pixel.y >= 0 && E >= 0 && E < w && pixel.y < h){
			Color color = new Color(buffImage.getRGB(E, pixel.y));
            grayValue = image.getGrayValue(color);
			if(grayValue == 255 && pixels[E][pixel.y] == 0){
				pixels[E][pixel.y] = label;
				Pixel neighbor = new Pixel(E, pixel.y);
				queue.add(neighbor);
				checkQueue(pixels, image, queue, label);
			}
		}
		if(pixel.y >= 0 && W >= 0 && W < w && pixel.y < h){
			Color color = new Color(buffImage.getRGB(W, pixel.y));
            grayValue = image.getGrayValue(color);
			if(grayValue == 255 && pixels[W][pixel.y] == 0){
				pixels[W][pixel.y] = label;
				Pixel neighbor = new Pixel(W, pixel.y);
				queue.add(neighbor);
				checkQueue(pixels, image, queue, label);
			}
		}
		return;
	}
	
	
	
	public int[][] labelImage(Image image){
		BufferedImage buffImage = image.getImage();
		int w = buffImage.getWidth();
		int h = buffImage.getHeight();
		Queue<Pixel> queue = new LinkedList<Pixel>();
		int grayValue;
		int[][] pixels = new int[w][h];
		
		int label = 1;
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				Color color = new Color(buffImage.getRGB(i, j));
	            grayValue = image.getGrayValue(color);
	            
	            if(grayValue == 255 && pixels[i][j] == 0){
	            	Pixel pixel = new Pixel(i,j);
	            	queue.add(pixel);
	            	checkQueue(pixels, image, queue, label);
	            	System.out.println("fdfasdf");
	            	label++;
	            }
	            
			}
		}
		
		return pixels;
	}
	
	public String saveImage(BufferedImage image){
		System.out.println("Guardado");
		File output = new File("C:/Users/pc/Desktop/Andrés/ACSP1/Proyecto1/resultados/image" +this.procImages + ".jpg");
		
		this.procImages = this.procImages + 1;
	    try {
			ImageIO.write(image, "jpg", output);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	    return "image" + (this.procImages-1);
	}

}
