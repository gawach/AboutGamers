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


@WebServlet("/UpdateGamerServlet")
public class UpdateGamerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateGamerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] strings = new String[6];
		strings[1] = request.getParameter("name");
		strings[2] = request.getParameter("teamName");
		strings[3] = request.getParameter("settings");
		strings[4] = request.getParameter("profile");
		strings[5] = request.getParameter("id");

		try(Connection con = DbUtility.connectionDb()) {
			new GamerDaoImpl().update(con, strings);
			request.setAttribute("ACTION_MESSAGE", "プレイヤー情報を更新しました");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("ACTION_MESSAGE", "更新に失敗しました");
		}
		response.sendRedirect("/GamerApp/GamerListServlet");
	}

}
