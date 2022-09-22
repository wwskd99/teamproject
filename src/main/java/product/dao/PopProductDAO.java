package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import product.dto.PopProductVO;
import util.DBManager;

public class PopProductDAO {
	private static PopProductDAO instance = null;
	public static PopProductDAO getInstance() {
		if(instance == null) {
			instance = new PopProductDAO();
		}
		return instance;
	}
	private PopProductDAO() { }
	
	// 제품 목록 출력
		public List<PopProductVO> popProductlist() {
			// code : auto_increment (1부터 시작 -> 내림차순) -> 최근 등록된 상품부터 보여주겠다.
			// 회원목록 : 정렬순서 (이름 가나다순, 최근 가입한 순서대로 -> 어떻게 정렬할 것인가? 정한다.)
			String sql = "select * from popsearch order by count desc";	// 최근 등록 순
			List<PopProductVO> list = new ArrayList<PopProductVO>();	// 빈 목록을 생성
			
			try (Connection conn = DBManager.getConnection();	
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					list.add(makePopProductVO(rs));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		// 메소드로 만들어 준다.
		private PopProductVO makePopProductVO(ResultSet rs) throws SQLException {
			PopProductVO popVo = new PopProductVO();
			popVo.setPopword(rs.getString("popword"));
			popVo.setCount(rs.getInt("count"));
			return popVo;
		}
}
