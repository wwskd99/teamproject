package product.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.dao.Cus_ProductDAO;
import common.command.CommandHandler;

public class PaymentSuccessHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			req.setCharacterEncoding("utf-8");
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String url = "/product/paymentSuccess.jsp";
		
		//cart_id만 있으면 code? amount? 불러오기가능
		
		String[] cart_id = req.getParameterValues("cart_id");
		
		// payment VO 에 저장해서 넘기는 방법으로 변환하기. 일단 쓴것..
		String order_name = req.getParameter("order_name");
		String order_email = req.getParameter("order_email");
		String order_phone = req.getParameter("order_phone");
		String del_name = req.getParameter("del_name");
		String del_phone = req.getParameter("del_phone");
		String del_address = req.getParameter("del_address");
		String del_message = req.getParameter("del_message");
		String del_date = req.getParameter("del_date");
		String paymethod = req.getParameter("paymethod");
		String del_pass = req.getParameter("del_pass");
		
		Cus_ProductDAO pDao = Cus_ProductDAO.getInstance();	
		
		pDao.setPayment(cart_id, order_name ,order_email ,order_phone ,del_name ,del_phone ,del_address ,del_message ,del_date ,paymethod ,del_pass);
		
		return url;
	}
}

