

import java.io.IOException;
import java.io.PrintWriter;

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
public class EditBlog extends GeneralServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.println(
				"<html>"
				+ "<head>"
				+ "<center><img src =\"http://img183.imageshack.us/img183/3439/ublog.png\"></center>"
				+ "<font face =\"Comic Sans MS\"><font color =\"purple\"><title>uBlog - Postagem</title>"
				+ "</head>"
				+ "<body background=\"http://1.bp.blogspot.com/_qTWVg4q5lCo/SMhLdkYwRwI/AAAAAAAAAKY/S-hS6iD4MnY/s1600/background.jpg\">"
				+ "<body>"
				+ "<h2 align=\"center\">Seu Blog</h2>"
				+ "<form method=\"post\" action=\"/blog/EditBlog\">"
				+ "<table border=\"0\" align=\"center\">"
				+ "<tr>"
				+ "<td><font face =\"Trebuchet MS\">Titulo do post*</td>"

				+ "<th><input name=\"titulo\" type=\"text\" size=\"30\" /></th>"
				+ "</tr>"
				+ "<tr>"
				
				+ "</tr>"
				+ "<tr>"
				+ "<th>"
				+ "<input name=\"salvar\" type=\"submit\" value=\"Salvar\" />"
				+ "<input name=\"delete\" type=\"submit\" value=\"Delete\" />"
				+ "</th>"

				+ "</tr>"
				+ "</table>"
				+ "</form>"
				+ "</body>"
				+ "</html>"
		);

		String error = request.getParameter("err");
		if (error != null) {
			if (error.equals("in")) {
				out
				.println("<h1 align = \"center\">Os dados estão incompletos.</h1>");
			}
		}


		out.println("</body>");

		out.println("</html>");
		out.flush();

	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String titulo = readParameter(request, "titulo");

		try{
			Blog blog = new Blog(titulo);
		}
		catch (Exception e) {
			response.sendRedirect("/blog/CreateBlogt?err=in");
		}
		response.sendRedirect("/blog/home?bl="+titulo);
	}



}

