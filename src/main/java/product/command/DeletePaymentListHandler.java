package product.command;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import product.dao.Cus_ProductDAO;

public class DeletePaymentListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}


	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException {
		int delivery_num = Integer.parseInt(req.getParameter("delivery_num"));
		Cus_ProductDAO pDao=Cus_ProductDAO.getInstance();
		pDao.deletePayment(delivery_num);
		res.sendRedirect("paymentlist.do");
		return null;
	}

}
