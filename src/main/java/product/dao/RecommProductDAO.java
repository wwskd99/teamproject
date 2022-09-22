package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import product.dto.ProductVO;
import util.DBManager;

public class RecommProductDAO {
	
	private static RecommProductDAO instance = null;
	public static RecommProductDAO getInstance() {
		if(instance == null) {
			instance = new RecommProductDAO();
		}
		return instance;
	}
	private RecommProductDAO() { }
	
	// 제품 목록 출력
		public List<ProductVO> Productlist() {
			// code : auto_increment (1부터 시작 -> 내림차순) -> 최근 등록된 상품부터 보여주겠다.
			// 회원목록 : 정렬순서 (이름 가나다순, 최근 가입한 순서대로 -> 어떻게 정렬할 것인가? 정한다.)
			String sql = "select p.*, pop.* from product p, popsearch pop where p.name = pop.popword order by pop.count desc";	// 최근 등록 순
			List<ProductVO> list = new ArrayList<ProductVO>();	// 빈 목록을 생성
			
			try (Connection conn = DBManager.getConnection();	
				PreparedStatement pstmt = conn.prepareStatement(sql);
					
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
}
