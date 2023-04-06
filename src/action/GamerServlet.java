package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbUtility;
import db.GamerDaoImpl;
import entity.Gamer;


@WebServlet("/GamerServlet")
public class GamerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GamerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String move = "Gamer.jsp";
		String gamerId = request.getQueryString();

		try(Connection con = DbUtility.connectionDb()) {
			Gamer gamer = new GamerDaoImpl().find(con, gamerId);
			request.setAttribute("GAMER", gamer);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher(move).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
