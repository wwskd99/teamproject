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



public class Cus_ProductDeleteHandler implements CommandHandler {


	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}


	private String processForm(HttpServletRequest req, HttpServletResponse res) {
	String url = "/product/cart.jsp";
		
		String[] check = req.getParameterValues("rowCheck");
		Cus_ProductDAO pDao = Cus_ProductDAO.getInstance();
		
		for (int i =0; i<check.length; i++) {
			
			int aCheck = Integer.parseInt(check[i]);
			pDao.productDelete(aCheck);
			
		}
		HttpSession session = req.getSession();
		MemberVO mVo = (MemberVO)session.getAttribute("loginUser");
		String user_id = mVo.getUserid();
		
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



