package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbUtility;
import db.GamerDaoImpl;
import entity.Gamer;

@WebServlet("/GamerListServlet")
public class GamerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GamerListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String move = "GamerList.jsp";
		try(Connection con = DbUtility.connectionDb()) {
			ArrayList<Gamer> gamers = new ArrayList<>();
			gamers = new GamerDaoImpl().findAll(con);
			request.setAttribute("GAMER_LIST", gamers);

			//ソート済み
			ArrayList<Gamer> sortGamers = new ArrayList<>();
			sortGamers = new GamerDaoImpl().sort(con);
			request.setAttribute("SORT_GAMER_LIST", sortGamers);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher(move).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
