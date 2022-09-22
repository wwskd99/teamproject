package product.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;

import product.dao.Cus_ProductDAO;
import product.dto.CartVO;


public class Cus_ProductBuyHandler implements CommandHandler {


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
		String url = "/product/cus_productBuy.jsp";
		
		String[] check = req.getParameterValues("rowCheck");
		Cus_ProductDAO pDao = Cus_ProductDAO.getInstance();
		
		ArrayList<CartVO> buylist = new ArrayList<CartVO>();
		buylist = pDao.productBuy(check);
		HttpSession session = req.getSession();
		session.setAttribute("buy",buylist);
		return url;
	}
}

