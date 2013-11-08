

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.Sistema;

/**
 * 
 * @author Karina Silva de Medeiros
 *  
 */

@SuppressWarnings("serial")
public class GeneralServlet extends HttpServlet {

	public String getWCSFilesPath() {
		return getServletContext().getRealPath("/users/");
	}

	public void sendNoAuthenticatedUserResponse(HttpServletResponse response)
			throws IOException {
		response.sendRedirect("/wcs/login");
	}

	public String readParameter(HttpServletRequest request, String param) {
		if (request.getParameter(param) != null) {
			return request.getParameter(param);
		} else {
			return "";
		}
	}

	public boolean isAuthenticated(HttpSession session) throws Exception {
		if (session == null) {
			return false;
		}
		String login = (String) session.getAttribute("login");
		String senha = (String) session.getAttribute("password");
		Sistema sis = Sistema.getInstance();
		if ((login == null) || (senha == null)) {
			return false;
		}
		return (sis.autenticar(login));

	}

}
