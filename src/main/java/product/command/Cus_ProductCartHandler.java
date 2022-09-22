package product.command;

import java.util.ArrayList;

import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.dto.MemberVO;
import product.dao.Cus_ProductDAO;
import product.dto.CartVO;


public class Cus_ProductCartHandler implements CommandHandler {


	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}


	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String url = "/product/cart.jsp";
		
		int code = Integer.parseInt(req.getParameter("code"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		HttpSession session = req.getSession();
		MemberVO mVo = (MemberVO)session.getAttribute("loginUser");
		String user_id = mVo.getUserid();
		
		Cus_ProductDAO pDao = Cus_ProductDAO.getInstance();
		pDao.insertCart(code,amount,user_id);
				
		
		
		// 위는 cart 테이블에 정보 저장
		// 아래는 페이지 조회를 위해 넣는거고..전에 세션에 정보를 저장해야 여러번 장바구니가도 다 등록할듯
		
		ArrayList<CartVO> cartlist = new ArrayList<CartVO>();
		try {
			cartlist = pDao.cartlist(user_id);
			session.setAttribute("cart",cartlist);
		
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
				

		return url;
		
	}
	
}




