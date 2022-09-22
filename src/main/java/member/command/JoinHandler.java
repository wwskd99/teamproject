package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.dao.MemberDAO;
import member.dto.MemberVO;

public class JoinHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return "/member/joinForm.jsp";
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {

		return "/member/joinForm.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String url = "/member/loginForm.jsp";

		String name = req.getParameter("name");
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String gender = req.getParameter("gender");
		
		MemberVO mVo = new MemberVO();
		
		mVo.setName(name);
		mVo.setUserid(userid);
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setAddress(address);
		mVo.setGender(Integer.parseInt(gender));
		mVo.setMile(0);
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.insertMember(mVo);
		HttpSession session = req.getSession();
		if (result == 1) {
			session.setAttribute("userid", mVo.getUserid());
			req.setAttribute("message", "회원 가입에 성공했습니다.");
		} else {
			req.setAttribute("message", "회원 가입에 실패했습니다.");
			url = "/member/joinForm.jsp";
		}
		return url;

	}
}
