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


@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String move = "searchResult.jsp";
		ArrayList<Gamer> gamers = new ArrayList<>();
		String[] keywords = new String[3];
		keywords[1] = request.getParameter("name");
		keywords[2] = request.getParameter("teamName");

		try(Connection con = DbUtility.connectionDb()) {
			gamers = new GamerDaoImpl().search(con, keywords);
			if (gamers.size() == 0) {
				throw new NullPointerException();
			}
			request.setAttribute("GAMER_LIST", gamers);
			request.setAttribute("COMPLETION_MESSAGE", gamers.size() +"件のデータが見つかりました");

			ArrayList<Gamer> sortGamers = new ArrayList<>();
			sortGamers = new GamerDaoImpl().sort(con);
			request.setAttribute("SORT_GAMER_LIST", sortGamers);
		} catch(NullPointerException e) {
			request.setAttribute("ERROR_MESSAGE", "データが見つかりませんでした");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher(move).forward(request, response);

	}

}
