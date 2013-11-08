

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.Blog;
import blog.Perfil;
import blog.Sistema;

/**
 * 
 * @author Karina Silva de Medeiros
 *  
 */

@SuppressWarnings("serial")
public class ViewProfile extends GeneralServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String blog  = readParameter(request, "bl");
		String nome  = readParameter(request, "nm");
		
		PrintWriter out = response.getWriter();
		String perfil = readParameter(request, "pf");
		Sistema sis = Sistema.getInstance();
		Blog b = null;
		String p = null;
		try {
			b = sis.getBlog(blog);
			p = b.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ver_perfil = p.toString();
		
		
		out.println(
				"<html>"
				+ "<head>"
				+ "<center><img src =\"http://img183.imageshack.us/img183/3439/ublog.png\"></center>"
				+ "<font face =\"Comic Sans MS\"><font color =\"purple\"><title>uBlog - Perfil</title>"
				+ "</head>"
				+ "<body background=\"http://1.bp.blogspot.com/_qTWVg4q5lCo/SMhLdkYwRwI/AAAAAAAAAKY/S-hS6iD4MnY/s1600/background.jpg\">"
				+ "<body>"
				+ "<h2 align=\"center\"></h2>"
				+ "<form method=\"post\" action=\"/blog/ViewProfile\">"
				+ "<table border=\"0\" align=\"center\">"
				+ "<tr>"
				+ "<div class=\"title\">"
				+ " <h2>"+nome+"</h2>"
				+ "</div>"
				+ "<div class=\"texto\">"
				+ "  <p>"+"<br />"
				+ " <br />"
				 + ver_perfil +" </p>"
				+ "</div>");
		
		
		out.println("</body>");

		out.println("</html>");
		out.flush();

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Perfil perfil;
		String nome = readParameter(request, "nome");
		int idade = Integer.parseInt(readParameter(request, "idade"));
		int sexo = Integer.parseInt(readParameter(request, "sexo"));
		String blog = request.getParameter("bl");
		String email = readParameter(request, "preferencias");
		String preferencias = readParameter(request, "preferencias");
		
		String data = "";
	       
        try{
            perfil = new Perfil(nome,idade,sexo,email,preferencias);
            data = perfil.toString();
        }
        catch (Exception e) {
            response.sendRedirect("/blog/EditProfile?err=in"+data);
        }
        Sistema sis = Sistema.getInstance();
		response.sendRedirect("/blog/home");
	}



}

