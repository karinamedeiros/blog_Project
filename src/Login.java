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
public class Login extends GeneralServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.println(
				"<head>" +
				"<center><img src =\"http://img183.imageshack.us/img183/3439/ublog.png\"></center>"
				+ "<font face =\"Comic Sans MS\"><font color =\"purple\"><title>uBLog!</title>"
				+ "</head>"

				+ "<body background=\"http://1.bp.blogspot.com/_qTWVg4q5lCo/SMhLdkYwRwI/AAAAAAAAAKY/S-hS6iD4MnY/s1600/background.jpg\">"

				+ "<body>"

				+ "<h1 align=\"center\">uBlog!</h1>"

				+ "<h2 align=\"center\">Login</h2>"

				+ "<form action=\"/blog/Login\" method=\"post\">"
				+ "<table border=\"0\" align=\"center\">"
				+ "<tbody>"
				+ "<tr>"
				+ "<td><font face =\"Trebuchet MS\">Usuario</td>"
				+ "<th><input name=\"login\" type=\"text\" size=\"15\" /></th>"
				+ "</tr>"

				+ "<tr>"
				+ "<td><font face =\"Trebuchet MS\">Senha</td>"
				+ "<th><input name=\"password\" type=\"password\" size=\"15\" /></th>"
				+ "</tr>"

				+ "</tbody>"

				+ "</table>"

				+ "<p align=\"center\">"
				+ "<input type=\"submit\" value=\"Login\" /> <a href=\"CreateAccount\"><font face =\"Trebuchet MS\">Ainda não possui uma conta? Crie a sua aqui!</a></p>"

				+ "</form>"

				+ "</body>"

				+ "</html>"
		);

		String error = request.getParameter("err");
		if (error != null) {
			if (error.equals("in")) 
				out.println("<h1 align = \"center\">Dados Invalidos</h1>");
			if (error.equals("na"))
				out.println("<h1 align = \"center\">Usuario ou senha incorretos.</h1>");
		}
		out.println("</body>");

		out.println("</html>");
		out.flush();

	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String login = readParameter(request, "user_name");
		String senha = readParameter(request, "senha");

		Sistema sis = Sistema.getInstance(); 
		boolean aut = false; 
		try{
			aut = sis.autenticar(login);
		}
		catch (Exception e) {
			response.sendRedirect("/blog/Login?err=in");
		} 
		if (!aut){
			response.sendRedirect("/blog/Login?err=na");
		}
		else{
			request.getSession().setAttribute("login",login);
			request.getSession().setAttribute("senha",senha);
			response.sendRedirect("/blog/perfil");
		}
	}

}

