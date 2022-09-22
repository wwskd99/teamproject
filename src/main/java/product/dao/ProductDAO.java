package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import product.dto.ProductVO;
import util.DBManager;

public class ProductDAO {
	private static ProductDAO instance = null;
	public static ProductDAO getInstance() {
		if(instance == null) {
			instance = new ProductDAO();
		}
		return instance;
	}
	private ProductDAO() { }
	
	// 제품 목록 출력
	public List<ProductVO> selectAllProducts() {
		// code : auto_increment (1부터 시작 -> 내림차순) -> 최근 등록된 상품부터 보여주겠다.
		// 회원목록 : 정렬순서 (이름 가나다순, 최근 가입한 순서대로 -> 어떻게 정렬할 것인가? 정한다.)
		String sql = "select * from product order by code desc";	// 최근 등록 순
		List<ProductVO> list = new ArrayList<ProductVO>();	// 빈 목록을 생성
		// try-with-resource : 주의사항
		// 참조변수를 선언하고 그 주소를 얻을 수 있으면 ()안에 포함을 시킨다.
		try (Connection conn = DBManager.getConnection();	// CP사용하는 메소드를 DBManager.java로 빼냄
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 중간에 자원을 선언하는 것 외에 다른 실행문이 존재하면 사용할 수 없다. (ResultSet)
			ResultSet rs = pstmt.executeQuery();){
			while(rs.next()) {
				list.add(makeProductVO(rs));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 메소드로 만들어 준다.
	private ProductVO makeProductVO(ResultSet rs) throws SQLException {
		ProductVO pVo = new ProductVO();
		pVo.setCode(rs.getInt("code"));
		pVo.setName(rs.getString("name"));
		pVo.setPrice(rs.getInt("price"));
		pVo.setPictureUrl(rs.getString("pictureurl"));
		pVo.setDescription(rs.getString("description"));
		pVo.setCategory_id(rs.getInt("category_id"));
		pVo.setCategory_name(rs.getString("category_name"));
		pVo.setStock(rs.getInt("stock"));
		pVo.setWritedate(rs.getTimestamp("writedate"));
		return pVo;
	}
	
	public void insertProduct(ProductVO pVo) {
		String sql = "insert into product (name, price, pictureurl, description, category_id, category_name, stock) values(?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.setInt(5, pVo.getCategory_id());
			pstmt.setString(6, pVo.getCategory_name());
			pstmt.setInt(7, pVo.getStock());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 제품에 대한 상세보기를 할 때, 제품 수정/삭제할 때 제품에 대한 정보를 보여주기 위해서 사용됨
	public ProductVO selectProductByCode(Integer code) {
		String sql = "select * from product where code = ?";
		ProductVO pVo = null;
		ResultSet rs = null;
		try (Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, code);	// 중간에 실행문이 있으면 ResultSet을 ()안에 포함시킬수 없음
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pVo = makeProductVO(rs);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);	// rs만 반납
		}
		return pVo;
	}
	
	// update시는 변경 가능한 것과 변경 불가능한 것을 구분해서 처리
	public void updateProduct(ProductVO pVo) {
		String sql = "update product set name=?, price=?, pictureurl=?, description=?, category_id=?, category_name=?, stock=? where code = ?";
		try (Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.setInt(5, pVo.getCategory_id());
			pstmt.setString(6, pVo.getCategory_name());
			pstmt.setInt(7, pVo.getStock());
			pstmt.setInt(8, pVo.getCode());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(Integer code) {
		String sql = "delete from product where code = ?";
		try (Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, code);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ProductVO> searchProductList(String keyword) {
		String sql = "select * from product where category_name like '%" + keyword + "%'";			
		String sql2 = "INSERT INTO popsearch (popword) VALUES (?) ON DUPLICATE KEY UPDATE popword=?, count=count+1";
		
		try (Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql2);){
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}			

		List<ProductVO> list = new ArrayList<ProductVO>();	// 빈 목록을 생성
		try (Connection conn = DBManager.getConnection();	// CP사용하는 메소드를 DBManager.java로 빼냄
			Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql); ){
			while(rs.next()) {
				list.add(makeProductVO(rs));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}