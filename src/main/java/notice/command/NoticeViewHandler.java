package notice.command;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeVO;

public class NoticeViewHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}

	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws NamingException {
		String url = "/notice/noticeView.jsp";
		int num = Integer.parseInt(req.getParameter("num"));
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.updateReadCount(num); // 게시글의 조회수를 증가
		NoticeVO bVo = bDao.selectOneBoardByNum(num);
		req.setAttribute("notice", bVo);
		return url;
	}
}
