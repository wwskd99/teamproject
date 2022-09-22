package notice.command;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeVO;

public class NoticeUpdateHandler implements CommandHandler {

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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws NamingException, IOException {
		NoticeVO bVo = new NoticeVO();
		bVo.setNum(Integer.parseInt(req.getParameter("num")));
		bVo.setTitle(req.getParameter("title"));
		bVo.setContent(req.getParameter("content"));
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.updateBoard(bVo);
		res.sendRedirect("list.do");
		return null;

	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws NamingException {
		String url = "/notice/noticeUpdate.jsp";
		int num = Integer.parseInt(req.getParameter("num"));
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.updateReadCount(num);
		NoticeVO bVo = bDao.selectOneBoardByNum(num);
		req.setAttribute("notice", bVo);
		return url;

	}


}
