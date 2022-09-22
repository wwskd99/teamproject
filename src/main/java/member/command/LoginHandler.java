package member.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.dao.MemberDAO;
import member.dto.MemberVO;

public class LoginHandler implements CommandHandler {
	/*
	 * get : 폼을 요청하거나 목록보기, 검색 post : 추가, 변경, 삭제 같은 데이터베이스를 변경하는 작업
	 * 
	 * 방법1: 두개(get,post)를 같은 핸들러에서 처리하도록 할수 있다. /member/login.do =
	 * member.command.LoginHandler 방법2: 서로 다른 핸들러가 처리하도록 할 수 있다.
	 * /member/loginForm.do = member.command.LoginFormHandler : get
	 * /member/loginPro.do = member.command.LoginProHandler : post
	 * 
	 * 현재는 방법 1을 사용하고있음
	 */
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String url = "/member/loginForm.jsp";
		HttpSession session = req.getSession();
		if (session.getAttribute("loginUser") != null) {
			url = "/common/login.jsp";
		}
		return url;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		String url = "/member/loginForm.jsp";
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String email_save = req.getParameter("checkbox");
	
		MemberDAO mDao = MemberDAO.getInstance();
		Cookie cookie = new Cookie("userId",userid);
		int result = mDao.userCheck(userid, pwd);
		if (result == 1) {
			MemberVO mVo = mDao.getMember(userid);
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", mVo);
			req.setAttribute("message", "로그인에 성공했습니다.");
			if(email_save != null) {	// 체크박스 눌럿으면
				cookie.setMaxAge(2592000);	// 한달동안 유지
				res.addCookie(cookie);
			} else {	// 누르지않앗으면
				cookie.setMaxAge(0); // 바로 삭제
				res.addCookie(cookie);
			}
			url = "/common/login.jsp";
		} else if (result == 0) {
			req.setAttribute("message", "비밀번호가 맞지 않습니다.");
		} else if (result == -1) {
			req.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		return url;

	}

}
