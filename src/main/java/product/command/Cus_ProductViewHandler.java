package product.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.dao.Cus_ProductDAO;
import product.dto.Cus_ProductVO;
import common.command.CommandHandler;

public class Cus_ProductViewHandler implements CommandHandler {

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
		String url = "/product/cus_productView.jsp";
		int code = Integer.parseInt(req.getParameter("code"));
		Cus_ProductDAO pDao = Cus_ProductDAO.getInstance();
		Cus_ProductVO pVo = pDao.selectProduct(code);
		req.setAttribute("product", pVo);
		return url;
	}
}

