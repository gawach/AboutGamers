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


@WebServlet("/DeleteGamerServlet")
public class DeleteGamerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteGamerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gamerId = request.getParameter("id");

		try(Connection con = DbUtility.connectionDb()) {
			new GamerDaoImpl().delete(con, gamerId);
			request.setAttribute("ACTION_MESSAGE", "ゲーマーを削除しました。");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			String move = "GamerList.jsp";
			request.setAttribute("ACTION_MESSAGE", "ゲーマーの削除に失敗しました。");
			request.getRequestDispatcher(move).forward(request, response);
		}
		response.sendRedirect("/GamerApp/GamerListServlet");
	}

}
