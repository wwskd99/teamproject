package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import product.dto.CartVO;
import product.dto.Cus_ProductVO;
import product.dto.PaymentVO;
import util.DBManager;

public class Cus_ProductDAO {
	private static Cus_ProductDAO instance = new Cus_ProductDAO();

	public static Cus_ProductDAO getInstance() {
		return instance;
	}

	public List<Cus_ProductVO> listUpProducts() {
		String sql = "select * from product order by writedate desc";

		List<Cus_ProductVO> list = new ArrayList<Cus_ProductVO>();

		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Cus_ProductVO vo = ProductVOFromResultSet(rs);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private Cus_ProductVO ProductVOFromResultSet(ResultSet rs) throws SQLException {
		Cus_ProductVO vo = new Cus_ProductVO();
		vo.setCode(rs.getInt("code"));
		vo.setName(rs.getString("name"));
		vo.setPrice(rs.getInt("price"));
		vo.setDescription(rs.getString("description"));
		vo.setPictureurl(rs.getString("pictureurl"));
		vo.setCategory_id(rs.getInt("category_id"));
		vo.setStock(rs.getInt("stock"));
		vo.setWritedate(rs.getString("writedate"));
		return vo;
	}

	public Cus_ProductVO selectProduct(Integer code) {
		String sql = "select * from product where code = ?";
		Cus_ProductVO pVo = null;
		ResultSet rs = null;
		// ?가 있을 때는 PreparedStatement를 사용한다.
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pVo = ProductVOFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
		}
		return pVo;
	}

	public void insertCart(Integer code, Integer amount, String user_id) {
		String sql = "insert into cart (code, user_id, amount) values (?, ?, ?)";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, code);
			pstmt.setString(2, user_id);
			pstmt.setInt(3, amount);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CartVO> cartlist(String user_id) throws NamingException {

		String sql = "select P.*, C.* from Product P, Cart C where C.user_id= ? and C.code = P.code";
		ArrayList<CartVO> cartlist = new ArrayList<CartVO>();
		ResultSet rs = null;

		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartVO cVo = new CartVO();
				cVo.setcode(rs.getInt("code"));
				cVo.setCategory_id(rs.getInt("category_id"));
				cVo.setName(rs.getString("name"));
				cVo.setPrice(rs.getInt("price"));
				cVo.setPictureurl(rs.getString("pictureurl"));
				cVo.setDescription(rs.getString("description"));
				cVo.setStock(rs.getInt("stock"));
				cVo.setWritedate(rs.getString("writedate"));
				cVo.setAmount(rs.getInt("amount"));
				cVo.setCart_id(rs.getInt("cart_id"));
				cartlist.add(cVo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartlist;

	}
	private PaymentVO PaymentVOFromResultSet(ResultSet rs) throws SQLException {
		PaymentVO vo = new PaymentVO();
		vo.setDelivery_num(rs.getInt("delivery_num"));
		vo.setCode(rs.getInt("code"));
		vo.setAmount(rs.getInt("amount"));
		vo.setUserid(rs.getString("userid"));
		vo.setOrder_name(rs.getString("order_name"));
		vo.setOrder_email(rs.getString("order_email"));
		vo.setOrder_phone(rs.getString("order_phone"));
		vo.setDel_name(rs.getString("del_name"));
		vo.setDel_phone(rs.getString("del_phone"));
		vo.setDel_address(rs.getString("del_address"));
		vo.setDel_message(rs.getString("del_message"));
		vo.setDel_date(rs.getString("del_date"));
		vo.setPaymethod(rs.getString("paymethod"));
		vo.setDel_pass(rs.getString("del_pass"));
		vo.setPost(rs.getInt("post"));
		return vo;
	}
	
	public List<PaymentVO> listUpPayments() {
		String sql = "select * from payment group by delivery_num order by payment_id";

		List<PaymentVO> list = new ArrayList<PaymentVO>();

		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				PaymentVO vo = PaymentVOFromResultSet(rs);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<PaymentVO> listPaymentDetails(int delivery_num) throws NamingException {
		String sql = "select p.*,pay.* from payment pay, product p where pay.delivery_num=? and pay.code=p.code order by payment_id";

		List<PaymentVO> list = new ArrayList<PaymentVO>();

		ResultSet rs = null;

		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, delivery_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PaymentVO pVo = new PaymentVO();
				Cus_ProductVO cusVo = new Cus_ProductVO();
				
				cusVo.setCode(rs.getInt("code"));
				cusVo.setName(rs.getString("name"));
				cusVo.setPrice(rs.getInt("price"));
				cusVo.setDescription(rs.getString("description"));
				cusVo.setPictureurl(rs.getString("pictureurl"));
				cusVo.setCategory_id(rs.getInt("category_id"));
				cusVo.setStock(rs.getInt("stock"));
				cusVo.setWritedate(rs.getString("writedate"));
				
				pVo.setDelivery_num(rs.getInt("delivery_num"));
				pVo.setCode(rs.getInt("code"));
				pVo.setAmount(rs.getInt("amount"));
				pVo.setUserid(rs.getString("userid"));
				pVo.setOrder_name(rs.getString("order_name"));
				pVo.setOrder_email(rs.getString("order_email"));
				pVo.setOrder_phone(rs.getString("order_phone"));
				pVo.setDel_name(rs.getString("del_name"));
				pVo.setDel_phone(rs.getString("del_phone"));
				pVo.setDel_address(rs.getString("del_address"));
				pVo.setDel_message(rs.getString("del_message"));
				pVo.setDel_date(rs.getString("del_date"));
				pVo.setPaymethod(rs.getString("paymethod"));
				pVo.setDel_pass(rs.getString("del_pass"));
				pVo.setPost(rs.getInt("post"));
				pVo.setCus_productVO(cusVo);
				list.add(pVo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<CartVO> productBuy(String[] sCheck) {

		String a = "";

		for (int i = 0; i < sCheck.length; i++) {

			if (i == (sCheck.length - 1)) {

				a += " ? ";

			} else {

				a += "? or C.cart_id = ";

			}

		}

		String sql = "select P.*, C.* from Product P, Cart C where (C.cart_id =" + a + ") and C.code = P.code";

		ArrayList<CartVO> buylist = new ArrayList<CartVO>();
		ResultSet rs = null;

		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			for (int i = 1; i < (sCheck.length + 1); i++) {
				pstmt.setString(i, sCheck[i - 1]);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartVO cVo = new CartVO();
				cVo.setcode(rs.getInt("code"));
				cVo.setCategory_id(rs.getInt("category_id"));
				cVo.setName(rs.getString("name"));
				cVo.setPrice(rs.getInt("price"));
				cVo.setPictureurl(rs.getString("pictureurl"));
				cVo.setDescription(rs.getString("description"));
				cVo.setStock(rs.getInt("stock"));
				cVo.setWritedate(rs.getString("writedate"));
				cVo.setAmount(rs.getInt("amount"));
				cVo.setCart_id(rs.getInt("cart_id"));
				buylist.add(cVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buylist;
	}

	public void productDelete(Integer aCheck) {

		String sql = "delete from cart where cart_id = ?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, aCheck);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPayment(String[] cart_id, String order_name, String order_email, String order_phone, String del_name,
			String del_phone, String del_address, String del_message, String del_date, String paymethod,
			String del_pass) {
		int delivery_num = (int) (Math.random() * 1000000000);
		CartVO cVo = new CartVO();
		int cartid;

		String sql = "insert into payment (delivery_num, code,amount,order_name,order_email,order_phone,del_name,del_phone,del_address,del_message, del_date,paymethod,del_pass,userid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		for (int i = 0; i < cart_id.length; i++) {
			cartid = Integer.parseInt(cart_id[i]);

			cVo = cartInfo(cartid);

			try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

				pstmt.setInt(1, delivery_num);

				pstmt.setInt(2, cVo.getcode());
				pstmt.setInt(3, cVo.getAmount());

				pstmt.setString(4, order_name);
				pstmt.setString(5, order_email);
				pstmt.setString(6, order_phone);
				pstmt.setString(7, del_name);
				pstmt.setString(8, del_phone);
				pstmt.setString(9, del_address);
				pstmt.setString(10, del_message);
				pstmt.setString(11, del_date);
				pstmt.setString(12, paymethod);
				pstmt.setString(13, del_pass);

				pstmt.setString(14, cVo.getUser_id());

				System.out.println("order_name : " + order_name);

				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public CartVO cartInfo(int cartid) {
		String sql = "select * from cart where cart_id = ?";
		ResultSet rs = null;
		CartVO cVo = new CartVO();
		int code = 0;

		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, cartid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cVo.setcode(rs.getInt("code"));
				cVo.setUser_id(rs.getString("user_id"));
				cVo.setAmount(rs.getInt("amount"));
				code = rs.getInt("code");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
		}

		String sql2 = "select * from product where code = ?";
		ResultSet rs2 = null;

		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt2 = conn.prepareStatement(sql2);) {

			pstmt2.setInt(1, code);
			rs2 = pstmt2.executeQuery();
			while (rs.next()) {
				cVo.setName(rs2.getString("name"));
				cVo.setPrice(rs2.getInt("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
		}

		return cVo;
	}
	
	public void deletePayment(int delivery_num) throws NamingException {
		String sql = "delete from payment where delivery_num=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, delivery_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
