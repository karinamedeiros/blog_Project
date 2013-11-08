

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
public class EditAccount extends GeneralServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.println("<head><center><img src =\"http://img183.imageshack.us/img183/3439/ublog.png\"></center> " +
				"<head><center><img src = \"http://img183.imageshack.us/img183/3439/ublog.png\"></center>" +
				"<font face =\"Comic Sans MS\"><font color =\"purple\"><title>Editar Informacoes de Usuario</title>" +
				"</head>" + 
				
				"<body background=\"http://1.bp.blogspot.com/_qTWVg4q5lCo/SMhLdkYwRwI/AAAAAAAAAKY/S-hS6iD4MnY/s1600/background.jpg\">" +
				
				"<h2 align=\"center\">Edite seu perfil uBlog:</h2>" + 
				
				"<form method=\"post\" action=\"/blog/EditAccount\">"  +  
				
				"<table border=\"0\" align=\"center\">"
				
				 +	"<tbody>"
				
						+ "<tr>"
							+ "<td><font face = \"Trebuchet MS\">Primeiro Nome</td>"
							+ "<th><input name=\"first_name\" type=\"text\" size=\"30\" /></th>"
						+ "</tr>"
				
						+ "<tr>"
							+ "<td><font face = \"Trebuchet MS\">Sobrenome</td>"
							+ "<th><input name=\"last_name\" type=\"text\" size=\"30\" /></th>"
						+ "</tr>"
				
						+"<tr>"
							+"<td>"+"<font face = \"Trebuchet MS\">Nome de Usuário </td>"
							+"<th>"+"<input name=\"user_name\" type=\"text\" size=\"30\" />"+"</th>"
						+"</tr>"
				
						+"<tr>"
							+"<td>"+"<font face = \"Trebuchet MS\">E-mail</td>"
							+"<th>"+"<input name=\"email\" type=\"text\" size=\"30\" />"+"</th>"
						+"</tr>"
				
						+"<tr>"
							+"<td>"+"<font face = \"Trebuchet MS\">Senha </td>"
							+"<th>"+"<input name=\"password\" type=\"password\" size=\"30\" />"+"</th>"
						+"</tr>"
				
						+"<tr>"
							+"<td>"+"<font face = \"Trebuchet MS\">Confirmar senha </td>"
							+"<th>"+"<input name=\"password_confirm\" type=\"password\" size=\"30\" />"+"</th>"
						+"</tr>"
				
					+"</tbody>"
				
				+"</table>"
				
				+"<p align=\"center\">"
				+"<input name=\"save\" type=\"submit\" value=\"Salvar\" />"
				+"<input name=\"cancel\" type=\"submit\" value=\"Cancelar\" />"
				+"</p>"
				
				+"</form>"
				
				+"</body>"
				
				+"</html>"
				);

		String error = request.getParameter("err");
		if (error != null) {
				if (error.equals("pw")) {
					out
					.println("<h1 align = \"center\">A senha está errada.</h1>");
				} else {
					if (error.equals("in")) {
						out
						.println("<h1 align = \"center\">Os dados estão incompletos.</h1>");
					}
				}
			}
		
		out.println("</body>");

		out.println("</html>");
		out.flush();

	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstName = readParameter(request, "first_name");
		String lastName = readParameter(request, "last_name");
		String login = readParameter(request, "user_name");
		String email = readParameter(request, "email");
		String senha = readParameter(request, "senha");
		String senhaRetyped = readParameter(request, "password_confirm");

		String data = "&fn=" + firstName + "&ln=" + lastName + "&lgn="
		+ login + "&email=" + email;
		Sistema sis = Sistema.getInstance(); 

		if (firstName.equals("") || lastName.equals("") || login.equals("")
				|| email.equals("") || senha.equals("")
				|| senhaRetyped.equals("")) {
			response.sendRedirect("/blog/EditAccount?err=in" + data);
		}else{
			Usuario user = null;
			try {
				user = sis.getUsuario(login);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (user == null) {
				response.sendRedirect("/blog/EditAccount?err=lgn" + data);
			}else{
				if (!(senha.equals(senhaRetyped))) {
					response.sendRedirect("/blog/EditAccount?err=pw" + data);
				}else{
					user.setLogin(firstName + " " + lastName, senhaRetyped);
					user.setEmail(email);
					response.sendRedirect("/blog/login");
				}
			}
		}

	}
}
