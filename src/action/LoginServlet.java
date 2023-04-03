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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try(Connection con = DbUtility.connectionDb()) {
			String[] strings = new String[3];
			strings[1] = request.getParameter("email");
			strings[2] = request.getParameter("pass");
			User loginUser = new UserDaoImpl().login(con, strings);

			HttpSession session = request.getSession();
			session.setAttribute("LOGIN_USER", loginUser);
			request.setAttribute("ACTION_MESSAGE", "ログインしました。");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			String move = "login.jsp";
			request.setAttribute("ACTION_MESSAGE", "ログインに失敗しました。");
			request.getRequestDispatcher(move).forward(request, response);
		}

		response.sendRedirect("/GamerApp/GamerListServlet");
	}
}
