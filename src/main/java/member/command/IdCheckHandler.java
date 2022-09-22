package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.MemberDAO;

public class IdCheckHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			String userid = req.getParameter("userid");
			MemberDAO mDao = MemberDAO.getInstance();
			int result = mDao.confirmID(userid);
			req.setAttribute("userid", userid);
			req.setAttribute("result", result);
			return "/member/idCheck.jsp";
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}

	}

}
