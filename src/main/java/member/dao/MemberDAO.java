package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.dto.MemberVO;
import util.DBManager;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();

	private MemberDAO() {
	};

	public static MemberDAO getInstance() {
		return instance;
	}

	// DBCP로부터 Connection을 가져오기
	public Connection getConnection() throws Exception {

		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/teamdb");
		return ds.getConnection();
	}

	// 인증 기능
	public int userCheck(String userid, String pwd) {
		int result = -1; // 인증실패
		String sql = "select pwd from member where userid = ?";
		ResultSet rs = null;
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 존재하는 회원
				// 사용자가 입력한 비밀번호와 데이터베이스의 비밀번호를 비교
				String dbPwd = rs.getString("pwd");
				if (dbPwd != null && pwd.equals(dbPwd)) { // 인증성공
					result = 1; // 인증 성공
				} else { // 인증 실패
					result = 0; // 비밀번호 불일치
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
		}
		return result;
	}

	public MemberVO getMember(String userid) {
		MemberVO mVo = null;
		String sql = "select * from member where userid = ?";
		ResultSet rs = null;
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mVo = new MemberVO();

				mVo.setName(rs.getString("name"));

				mVo.setUserid(rs.getString("userid"));

				mVo.setPwd(rs.getString("pwd"));

				mVo.setEmail(rs.getString("email"));

				mVo.setPhone(rs.getString("phone"));
				
				mVo.setAddress(rs.getString("address"));

				mVo.setGrade(rs.getInt("grade"));
				
				mVo.setGender(rs.getInt("gender"));
				
				mVo.setMile(rs.getInt("mile"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
		}
		return mVo;
	}
	
	public MemberVO getMemberPwd(String userid, String name) {
		MemberVO mVo = null;
		String sql = "select * from member where name=? and userid = ?";
		ResultSet rs = null;
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mVo = new MemberVO();

				mVo.setName(rs.getString("name"));

				mVo.setUserid(rs.getString("userid"));

				mVo.setPwd(rs.getString("pwd"));
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
		}
		return mVo;
	}

	public int confirmID(String userid) {
		int result = -1;
		String sql = "select userid from member where userid = ?";
		ResultSet rs = null;
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
		}
		return result;
	}

	public int insertMember(MemberVO mVo) {
		int result = -1;
		String sql = "insert into member values (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getAddress());
			pstmt.setString(6, mVo.getPhone());
			pstmt.setInt(8, mVo.getGender());
			pstmt.setInt(9, mVo.getMile());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateMember(MemberVO mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?, address=?, phone=?, gender=?" + " where userid=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getAddress());
			pstmt.setString(4, mVo.getPhone());
			pstmt.setInt(6, mVo.getGender());
			pstmt.setString(7, mVo.getUserid());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int grantMember(MemberVO mVo) {
		int result = -1;
		String sql = "update member set grade=? where userid=?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
		
			pstmt.setInt(1, mVo.getGrade());
			pstmt.setString(2, mVo.getUserid());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public List<MemberVO> selectAllMembers() throws NamingException {
		String sql = "select * from member";
		List<MemberVO> list = new ArrayList<MemberVO>();
		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				// 중복되는 부분을 메소드로 만들어 준다.
				MemberVO mVo = new MemberVO();
				mVo.setName(rs.getString("name"));
				mVo.setUserid(rs.getString("userid"));
				mVo.setPwd(rs.getString("pwd"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setAddress(rs.getString("address"));
				mVo.setGrade(rs.getInt("grade"));
				mVo.setGender(rs.getInt("gender"));
				mVo.setMile(rs.getInt("mile"));
				list.add(mVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
/*	public void cryptPassword() {
		String sql = "select userid, pwd from member";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// SHA-256 객체의 인스턴스를 구한다.
		SHA256 sha = SHA256.getInsatnce();
		
		try(Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement();) {
			rs = stmt.executeQuery(sql);
			// 모든 사용자의 비밀번호를 암호화하여 저장
			while(rs.next()) {	// 모든 사용자마다 암호화
				String userid = rs.getString("userid");
				String orgPass = rs.getString("pwd");
				// SHA256 클래스의 getSha256() 메소드를 사용해서
				// 암호화된지 않은 비밀번호를 SHA256 방식으로 암호화
				String shaPass = sha.getSha256(orgPass.getBytes());
				
				// SHA256방식으로 암호화된 값을 다시 BCrypt 클래스의
				// hashpw()메소드를 사용해서 BCrypt 방식으로 암호화 수행
				String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
				
				// 데이터베이스에 저장
				pstmt = conn.prepareStatement(
						"update member set pwd=? where userid=?");
				pstmt.setString(1, bcPass);
				pstmt.setString(2, userid);
				pstmt.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, rs);
		}
	}
	*/
	
}
