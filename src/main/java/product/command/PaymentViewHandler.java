package product.command;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.dao.Cus_ProductDAO;
import product.dto.PaymentVO;
import common.command.CommandHandler;

public class PaymentViewHandler implements CommandHandler {

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
		String url = "/product/paymentView.jsp";
		int delivery_num = Integer.parseInt(req.getParameter("delivery_num"));
		
		Cus_ProductDAO pDao = Cus_ProductDAO.getInstance();
		List<PaymentVO> payVo = pDao.listPaymentDetails(delivery_num);
		req.setAttribute("payment", payVo);
		return url;
	}
}

