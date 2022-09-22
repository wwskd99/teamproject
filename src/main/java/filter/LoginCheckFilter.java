package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        boolean login = false;
        if (session != null) {
            if (session.getAttribute("loginUser") != null) {	// 로그인 여부를 판별
                login = true;
            }
        }
        if (login) {	// 로그인이 되었으면 url 접근을 허용
            chain.doFilter(request, response);
        } else {	// 로그인인 되지 않았으면 로그인을 하도록 요청
            RequestDispatcher dispatcher = request.getRequestDispatcher("/member/loginForm.jsp");
            dispatcher.forward(request, response);
        }

	}

}
