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


@WebServlet("/InsertGamerServlet")
public class InsertGamerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertGamerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] strings = new String[5];
		strings[1] = request.getParameter("name");
		strings[2] = request.getParameter("teamName");
		strings[3] = request.getParameter("settings");
		strings[4] = request.getParameter("profile");

		try(Connection con = DbUtility.connectionDb()) {
			new GamerDaoImpl().insert(con, strings);
			request.setAttribute("COMPLETION_MESSAGE", "プレイヤーを登録しました");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("ERROR_MESSAGE", "プレイヤーを登録できませんでした");
		}

		response.sendRedirect("/GamerApp/GamerListServlet");
	}

}
