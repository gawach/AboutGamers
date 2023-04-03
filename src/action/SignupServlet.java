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

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String move = "signup.jsp";
		String[] strings = new String[4];
		String passConfirm = request.getParameter("passConfirm");
		strings[1] = request.getParameter("name");
		strings[2] = request.getParameter("email");
		strings[3] = request.getParameter("pass");

		try(Connection con = DbUtility.connectionDb()) {
			//パスワード確認と不一致のとき
			if(!strings[3].equals(passConfirm)) {
				throw new NumberFormatException();
			}
			User signupUser = new UserDaoImpl().signup(con, strings);
			HttpSession session = request.getSession();
			session.setAttribute("LOGIN_USER", signupUser);
		} catch (NumberFormatException e) {
			request.setAttribute("ACTION_MESSAGE", "パスワードが一致しません");
			request.getRequestDispatcher(move).forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("ACTION_MESSAGE", "ユーザーを登録できませんでした");
			request.getRequestDispatcher(move).forward(request, response);
		}
		response.sendRedirect("/GamerApp/GamerListServlet");
	}

}
