package member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.dao.MemberDAO;
import member.dto.MemberVO;

public class GrantUserHandler implements CommandHandler {
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

//		req.setCharacterEncoding("UTF-8"); // 한글 깨짐을 방지
		// 폼에서 입력한 회원 정보 얻어오기
		String[] userid = req.getParameterValues("userid");
		String[] grade = new String[userid.length];
		for(int i=0; i<grade.length; i++) {
			grade[i] = req.getParameter("grantcheck"+i);
		}

		for (int i = 0; i < grade.length; i++) {
			MemberVO mVo = new MemberVO(); // 회원 정보를 저장할 객체 생성
			mVo.setUserid(userid[i]);
			mVo.setGrade(Integer.parseInt(grade[i]));

			MemberDAO dao = MemberDAO.getInstance();
			int result = dao.grantMember(mVo);
		}
		res.sendRedirect("login.do");

		return null;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		return "/member/grantList.jsp";
	}
}
