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
import db.UserDaoImpl;
import entity.User;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signup.jsp").forward(request, response);
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
			request.getSession().setAttribute("LOGIN_USER", signupUser);
			request.getSession().setAttribute("COMPLETION_MESSAGE", "ユーザーが登録されました");
		} catch (NumberFormatException e) {
			request.getSession().setAttribute("ERROR_MESSAGE", "パスワードが一致しません");
			request.getRequestDispatcher(move).forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("ERROR_MESSAGE", "ユーザーを登録できませんでした");
			request.getRequestDispatcher(move).forward(request, response);
		}
		response.sendRedirect("/GamerApp/GamerListServlet");
	}

}
