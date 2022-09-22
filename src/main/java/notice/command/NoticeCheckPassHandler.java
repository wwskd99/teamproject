package notice.command;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeVO;

public class NoticeCheckPassHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return "/notice/checkSuccess.jsp";
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws NamingException {
		String url = null;
//		int num = Integer.parseInt(req.getParameter("num"));
//		String pass = req.getParameter("pass");
//		NoticeDAO bDao = NoticeDAO.getInstance();
//		NoticeVO bVo = bDao.checkPassWord(pass, num);
//		if (bVo == null) { // 실패
//			url = "/notice/noticeCheckPass.jsp";
//			req.setAttribute("message", "비밀번호가 틀렸습니다.");
//		} else {// 성공
			url = "/notice/checkSuccess.jsp";
//		}
		return url;
	}

}
