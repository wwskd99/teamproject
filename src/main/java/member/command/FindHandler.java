package member.command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.dao.MemberDAO;
import member.dto.MemberVO;

/**
 * Servlet implementation class FindHandler
 */
@WebServlet("/find.do")
public class FindHandler implements CommandHandler{
       
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return "/member/findpwd.jsp";
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {

		return "/member/findpwd.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String url =  "/member/findpwd.jsp";

		String name = req.getParameter("name");
		String userid = req.getParameter("userid");
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO result = mDao.getMemberPwd(userid, name);
		HttpSession session = req.getSession();
		if (result.getPwd()!=null) {
			session.setAttribute("pwd", result.getPwd());
			req.setAttribute("message", "비밀번호 찾기에 성공했습니다.");
		} else {
			req.setAttribute("message", "비밀번호 잧기에 실패했습니다.");
			url = "/member/findpwd.jsp";
		}
		return url;

	}
}

