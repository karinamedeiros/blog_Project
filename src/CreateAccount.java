import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.Sistema;
import blog.Usuario;

/**
 * 
 * @author Karina Silva de Medeiros
 * @version 1.01 10 de Julho de 2009
 *
 */
@SuppressWarnings("serial")
public class CreateAccount extends GeneralServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// load existent
		String firstName = readParameter(request, "fn");
		String lastName = readParameter(request, "ln");
		String login = readParameter(request, "lgn");
		String email = readParameter(request, "email");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out
		.println("<title>Crie uma conta no uBlog!</title>");
		out.println("</head>");
		out.println("<body style=\"background: #EBEBEB url(http://1.bp.blogspot.com/_qTWVg4q5lCo/SMhLdkYwRwI/AAAAAAAAAKY/S-hS6iD4MnY/s1600/background.jpg) repeat-x;\">");

		out.println("<font face =\"Comic Sans MS\">");

		out.println("<center><img src =\"http://img183.imageshack.us/img183/3439/ublog.png \"></center>");

		out.println("<h2 align=\"center\">Criando uma Conta uBlog:</h2>");

		out.println("<form method=\"post\" action=\"/blog/CreateAccount\">");

		out.println("<table border=\"0\" align=\"center\">");
		out.println("<tbody>");
		out
		.println("<tr>");
		out.println("<td><font face =\"Trebuchet MS\">Primeiro Nome*</td>");

		out.println("<th><input name=\"first_name\" type=\"text\" size=\"30\" /></th>");
		out.println("</tr>");
		out
		.println("<tr>");
		out.println("<td><font face =\"Trebuchet MS\">Sobrenome</td>");

		out.println("<th><input name=\"last_name\" type=\"text\" size=\"30\" /></th>");
		out.println("</tr>");
		out
		.println("<tr>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td><font face =\"Trebuchet MS\">Nome do usuario*</td>");
		out
		.println("<th><input name=\"user_name\" type=\"text\" size=\"30\" /></th>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td><font face =\"Trebuchet MS\">Sexo*</td>");
		out
		.println("<th><input name=\"sexo\" type=\"text\" size=\"30\" /></th>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td><font face =\"Trebuchet MS\">E-mail*</td>");
		out
		.println("<th><input name=\"email\" type=\"text\" size=\"30\" /></th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><font face =\"Trebuchet MS\">Senha*</td>");
		out
		.println("<th><input name=\"senha\" type=\"password\" size=\"30\" /></th>");
		out.println("</tr>");

		out.println("<tr>");

		out.println("<td><font face =\"Trebuchet MS\">Confirmar senha*</td>");

		out
		.println("<th><input name=\"password_confirm\" type=\"password\" size=\"30\" /></th>");

		out.println("</tr>");

		out.println("</tbody>");

		out.println("</table>");

		out.println("<p align=\"center\"><input type=\"submit\" value=\"Criar Conta!\" /> <ahref=\"login.html\">Cancelar</a></p>");

		out.println("<center><td><font face =\"Trebuchet MS\">Os campos marcados por * sao obrigatorios.</td></center>");

		out.println("</form>");

		out.println("</font>");
		out.println("</body>");
		out.println("</html>");

		String error = request.getParameter("err");
		String data = request.getParameter("data");
		if (error != null) {
			if (error.equals("lgn")) {
				out
				.println("<h1 align = \"center\">Este login já existe.</h1>");
			} else {
				if (error.equals("pw")) {
					out
					.println("<h1 align = \"center\">A senha está errada.</h1>");
				} else {
					if (error.equals("in")) {
						out
						.println("<h1 align = \"center\">Os dados estão incompletos.</h1>"+
								"<h1 align = \"center\">"+data+"</h1>");
					}
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
		String sexo = readParameter(request, "sexo");
		String senha = readParameter(request, "senha");
		String senhaRetyped = readParameter(request, "password_confirm");

		String data = "&fn=" + firstName + "&ln=" + lastName + "&lgn="
		+ login + "&email=" + email;
		Sistema sis = Sistema.getInstance(); 

		if (firstName.equals("") || lastName.equals("") || login.equals("")
				|| email.equals("") || senha.equals("")
				|| senhaRetyped.equals("")) {
			response.sendRedirect("/blog/CreateAccount?err=in" + data);
		}else{
			try {
				if (sis.getUsuario(login) != null) 
					response.sendRedirect("/blog/CreateAccount?err=lgn" + data);
				else if (!(senha.equals(senhaRetyped))) 
						response.sendRedirect("/blog/CreateAccount?err=pw" + data);
					else{
						sis.criaUsuario(login,senha,email);
						response.sendRedirect("/blog/Login");	
					}
			}catch (Exception e) {
				String data2 = e.getMessage();
				response.sendRedirect("/blog/CreateAccount?err=in&data="+data2);
			}
		}

	}
}
