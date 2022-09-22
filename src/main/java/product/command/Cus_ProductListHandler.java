package product.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import product.dao.Cus_ProductDAO;
import product.dto.Cus_ProductVO;


public class Cus_ProductListHandler implements CommandHandler {


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
		String url = "/product/cus_productList.jsp";
		Cus_ProductDAO pDao = Cus_ProductDAO.getInstance();
		List<Cus_ProductVO> productList = pDao.listUpProducts();
		req.setAttribute("productList", productList);
		return url;
	}
}