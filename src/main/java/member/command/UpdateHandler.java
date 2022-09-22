package member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.dao.MemberDAO;
import member.dto.MemberVO;

public class UpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
//		req.setCharacterEncoding("UTF-8"); // 한글 깨짐을 방지
		// 폼에서 입력한 회원 정보 얻어오기
		String name = req.getParameter("name");
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String grade = req.getParameter("grade");
		String gender = req.getParameter("gender");
		
		HttpSession session = req.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser==null) {
			res.sendRedirect("login.do");

		} else if(!userid.equals(loginUser.getUserid())) {
			res.sendRedirect("login.do");

		} else {
			MemberVO mVo = new MemberVO();	// 회원 정보를 저장할 객체 생성
			mVo.setName(name);
			mVo.setUserid(userid);
			mVo.setPwd(pwd);
			mVo.setEmail(email);
			mVo.setPhone(phone);
			mVo.setAddress(address);
			mVo.setGrade(Integer.parseInt(grade));
			mVo.setGender(Integer.parseInt(gender));
			
			MemberDAO dao = MemberDAO.getInstance();
			int result = dao.updateMember(mVo);
			session.setAttribute("loginUser", mVo);
			res.sendRedirect("login.do");

		}
		
		return null;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String userid = req.getParameter("userid");
		HttpSession session = req.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser == null) {
			res.sendRedirect("login.do");
			return null;
		} else if(!userid.equals(loginUser.getUserid())){
			res.sendRedirect("login.do");
			return null;
		} else {
			MemberDAO mDao = MemberDAO.getInstance();
			MemberVO mVo = mDao.getMember(userid);
			req.setAttribute("mVo", mVo);

			return "/member/memberUpdateForm.jsp";
		}

	}

}
