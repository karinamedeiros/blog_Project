

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.*;

/**
 * 
 * @author Karina Silva de Medeiros
 *  
 */

@SuppressWarnings("serial")
public class ViewBlog extends GeneralServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String blog  = readParameter(request, "bl");
		String titulo  = readParameter(request, "ti");
		
		
		PrintWriter out = response.getWriter();
		String postagem = readParameter(request, "pt");
		String data = "&bl=" + blog + "&pt="+ postagem;
		Sistema sis = Sistema.getInstance();
		Blog b = null;

		try {
			 b = sis.getBlog(blog);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		out.println(
				"<html>"
				+ "<head>"
				+ "<center><img src =\"http://img183.imageshack.us/img183/3439/ublog.png\"></center>"
				+ "<font face =\"Comic Sans MS\"><font color =\"purple\"><title>uBlog - Postagem</title>"
				+ "</head>"
				+ "<body background=\"http://1.bp.blogspot.com/_qTWVg4q5lCo/SMhLdkYwRwI/AAAAAAAAAKY/S-hS6iD4MnY/s1600/background.jpg\">"
				+ "<body>"
				+ "<h2 align=\"center\"></h2>"
				+ "<form method=\"post\" action=\"/ublog/PostPage\">"
				+ "<table border=\"0\" align=\"center\">"
				+ "<tr>"
				+ "<div class=\"title\">"
				+ " <h2>"+titulo+"</h2>"
				+ "</div>"
				+ "<div class=\"texto\">"
				+ "  <p>"+"<br />"
				+ " <br /></p>"
				+ "</div>");
		Iterator<String> it = b.getPostsTitles().iterator();
		while (it.hasNext())
		out.println(it.next());
	
		out.println( "</body>"
			+ "</html>"	);

		String error = request.getParameter("err");
		if (error != null) {
			if (error.equals("in")) {
				out
				.println("<h1 align = \"center\">The data is incomplete.</h1>");
			}
		}
	
		
		out.println("</body>");

		out.println("</html>");
		out.flush();

	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String conteudo = readParameter(request, "conteudo");
		String titulo = readParameter(request, "titulo");
		String blog = request.getParameter("bl");
		String data = "&ti=" +titulo + "&ct=" + conteudo;
		Postagem post = null;
		try{
			post = new Postagem(titulo,conteudo);
		}
		catch (Exception e) {
			response.sendRedirect("/blog/EditPost?err=in"+data);
		}
		Sistema sis = Sistema.getInstance();
		try {
			Blog b = sis.getBlog(blog);
			b.addPost(post);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("/blog/home");
	}



}

