package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEditGamerServlet")
public class UpdateEditGamerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateEditGamerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String move = "updateGamer.jsp";

		request.setAttribute("ID", request.getParameter("id"));
		request.setAttribute("NAME", request.getParameter("name"));
		request.setAttribute("TEAM_NAME", request.getParameter("teamName"));
		request.setAttribute("SETTINGS", request.getParameter("settings"));
		request.setAttribute("PROFILE", request.getParameter("profile"));

		request.getRequestDispatcher(move).forward(request, response);
	}

}
