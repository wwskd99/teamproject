package product.command;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import product.dao.ProductDAO;
import product.dto.ProductVO;

public class ProductSearchHandler implements CommandHandler {
	
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
		String url = "/product/productList.jsp";
		String keyword = req.getParameter("search");
		ProductDAO pDao = ProductDAO.getInstance();
		List<ProductVO> productList = pDao.searchProductList(keyword);
		req.setAttribute("productList", productList);
		
		return url;
	}
	
}
