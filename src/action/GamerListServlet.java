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
import javax.servlet.http.HttpSession;

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
		String page = request.getQueryString();
		String launchButton_totalPageNum[] = new String[2];
		try(Connection con = DbUtility.connectionDb()) {
			//ページ処理
			if (page == null || page == "") {
				page = "1";
			}
			launchButton_totalPageNum = new GamerDaoImpl().adjustPaging(con, page);
			ArrayList<Gamer> gamers = new ArrayList<>();
			gamers = new GamerDaoImpl().findPerPage(con, page);
			request.setAttribute("GAMER_LIST", gamers);

			//ソート済み
			ArrayList<Gamer> sortGamers = new ArrayList<>();
			sortGamers = new GamerDaoImpl().sort(con);
			request.setAttribute("SORT_GAMER_LIST", sortGamers);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//メッセージ引き継ぎ
		HttpSession session = request.getSession();
		String compMsg = (String)session.getAttribute("COMPLETION_MESSAGE");
		if (compMsg != null) {
			request.setAttribute("COMPLETION_MESSAGE", compMsg);
			session.removeAttribute("COMPLETION_MESSAGE");
		}
		String errMsg = (String)session.getAttribute("ERROR_MESSAGE");
		if (errMsg != null) {
			request.setAttribute("ERROR_MESSAGE", errMsg);
			session.removeAttribute("ERROR_MESSAGE");
		}
		request.setAttribute("CURRENT_PAGE", page);
		request.setAttribute("BUTTON", launchButton_totalPageNum[0]);
		request.setAttribute("TOTAL_PAGE_NUM", launchButton_totalPageNum[1]);
		request.getRequestDispatcher(move).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
