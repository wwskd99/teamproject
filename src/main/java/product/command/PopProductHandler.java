package product.command;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import product.dao.PopProductDAO;
import product.dao.RecommProductDAO;
import product.dto.PopProductVO;
import product.dto.ProductVO;

public class PopProductHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm1(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm1(HttpServletRequest req, HttpServletResponse res) throws NamingException {
		String url = "/product/popSearch.jsp";
		PopProductDAO Dao = PopProductDAO.getInstance();
		List<PopProductVO> popList = Dao.popProductlist();
		req.setAttribute("popList", popList);
	
		//추전제품 데베를 전송

		RecommProductDAO rDao = RecommProductDAO.getInstance();
		List<ProductVO> recommList = rDao.Productlist();
		req.setAttribute("recommList", recommList);
		
		return url;
	}

}
