package notice.command;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeVO;

public class NoticeWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return "/notice/noticeWrite.jsp";
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
//	private String processForm(HttpServletRequest req, HttpServletResponse res) {
//		return "/board/boardWrite.jsp";
//	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException {
		// 브라우저에서 올라오는 데이터
		NoticeVO bVo = new NoticeVO();
		bVo.setTitle(req.getParameter("title"));
		bVo.setContent(req.getParameter("content"));
		
		// 데베에 추가
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.insertBoard(bVo);
		
		//목록보기로 리다이렉트
		res.sendRedirect("list.do");
		return null;
	}
}
