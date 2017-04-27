package imageProcessing;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class imageServlet
 */
@WebServlet("/imageServlet")
public class imageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = request.getParameter("ruta");
		
		/*
		 Aqu� va el c�digo del main 
		 
		 recibiendo como parametro la ruta
		 */
		
		//System.out.print(path);
		
		ImageManager controlador = new ImageManager();
		
		
		Image i = controlador.createImage(path);
		Kittler k = new Kittler();
		k.kittler(i);
		ImageTagger it = new ImageTagger();
		
		it.segmentImage(i);
		
		System.out.println(i.getT());
		
	}

}
