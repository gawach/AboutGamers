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
		String[] keywords = new String[3];
		keywords[1] = request.getParameter("name");
		keywords[2] = request.getParameter("teamName");

		try(Connection con = DbUtility.connectionDb()) {
			request.setAttribute("GAMER_LIST", new GamerDaoImpl().search(con, keywords));
			request.setAttribute("ACTION_MESSAGE", "検索結果一覧");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("ACTION_MESSAGE", "検索に失敗しました");
			response.sendRedirect("/GamerApp/GamerListServlet");
		}

		request.getRequestDispatcher(move).forward(request, response);

	}

}
