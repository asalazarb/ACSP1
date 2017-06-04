import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class Proyecto1Test {
	
	ImageManager controlador;
	Image i;
	@Before
	public void before(){
		controlador = new ImageManager();
		i = controlador.createImage("testImages/img1.png");
	}
	
	
	@Test
	public void Verificar_el_valor_T_de_imagen(){
		Kittler k = new Kittler();
		k.kittler(i);
		
		double resultado = i.getT();
		
		double esperado = 1.5572213501343157;
		
		assertTrue(resultado == esperado);
	}
	
	@Test
	public void Verificar_Histograma_Normalizado(){
		
		HashMap<Integer, Double> histograma = i.normHistogram ;
		
		for(int key : histograma.keySet()){
			double keyVal = histograma.get(key);

			assertTrue("Error, el valor debe ser menor que 1", keyVal <= 1);
			assertTrue("Error, el valor debe ser mayor que 0",  keyVal >= 0);
		}
	}
	
	
	@Test(timeout = 200)
	public void algoritmo_Kittler_Optimo(){
		Kittler k = new Kittler();
		k.kittler(i);
		
	}
	
	
	@Test(timeout = 100)
	public void Conversion_escala_de_grises_optimo(){
		controlador.GrayScaleConversion("testImages/img1.png");
	}
	
	
	
	@Test(timeout = 200)
	public void Interaccion_ImageTagger_ImageManager(){
		ImageTagger it = new ImageTagger();
		it.segmentImage(i);
	}
	
	@Test(timeout = 800)
	public void Imagen_almacenada_depues_de_ejecutar_algoritmo(){
		Kittler k = new Kittler();
		k.kittler(i);
		ImageTagger it = new ImageTagger();
		it.segmentImage(i);
		
	}
	
	
	
	@Test(timeout = 200)
	public void CargarImagen_en_menos_de_200_milisegundos(){		
		controlador.createImage("testImages/img1.png");
	}
	
	
	
	
	
	
	
}
