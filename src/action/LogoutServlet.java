package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DbUtility;
import db.UserDaoImpl;
import entity.User;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try(Connection con = DbUtility.connectionDb()) {

			User user = (User)session.getAttribute("LOGIN_USER");
			new UserDaoImpl().logout(con, user.getId_user());
			session.removeAttribute("LOGIN_USER");
			session.setAttribute("COMPLETION_MESSAGE", "ログアウトしました");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			session.setAttribute("ERROR_MESSAGE", "ログアウトに失敗しました");
		}

		response.sendRedirect("/GamerApp/GamerListServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
