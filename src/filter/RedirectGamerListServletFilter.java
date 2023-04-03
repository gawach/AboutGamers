package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/GamerList.jsp")
public class RedirectGamerListServletFilter implements Filter {

    public RedirectGamerListServletFilter() {

    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		chain.doFilter(request, response);
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.sendRedirect("/GamerApp/GamerListServlet");
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
