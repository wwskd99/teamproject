package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import notice.dto.NoticeVO;
import util.DBManager;

public class NoticeDAO {
	private NoticeDAO() {}

	private static NoticeDAO instance = new NoticeDAO();

	public static NoticeDAO getInstance() {
		return instance;
	}

	public List<NoticeVO> selectAllBoards() throws NamingException {
		String sql = "select * from notice order by num desc";
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				NoticeVO bVo = getBoardVOFromResultSet(rs);
				list.add(bVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private NoticeVO getBoardVOFromResultSet(ResultSet rs) throws SQLException {
		NoticeVO bVo = new NoticeVO();
		bVo.setNum(rs.getInt("num"));
		bVo.setTitle(rs.getString("title"));
		bVo.setContent(rs.getString("content"));
		bVo.setReadcount(rs.getInt("readcount"));
		bVo.setWritedate(rs.getTimestamp("writedate"));
		return bVo;
	}

	public int insertBoard(NoticeVO bVo) throws NamingException {
		String sql = "insert into notice(title, content) values(?, ?)";
		int result=-1;
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updateReadCount(int num) throws NamingException { // 게시글 조회 횟수 갱신
		String sql = "update notice set readcount=readcount+1 where num=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	// 게시판 글 상세 내용 보기 :글번호로 찾아온다. : 실패 null,
	public NoticeVO selectOneBoardByNum(int num) throws NamingException {
		String sql = "select * from notice where num = ?";
		NoticeVO bVo = null;
		ResultSet rs = null;
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bVo = getBoardVOFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
		}
		return bVo;
	}

	public int updateBoard(NoticeVO bVo) throws NamingException {
		String sql = "update notice set title=?, content=? where num=?";
		int result = -1;
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getContent());
			pstmt.setInt(3, bVo.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public NoticeVO checkPassWord(String pass, int num) throws NamingException { // 비밀번호 확인
		String sql = "select * from notice where pass=? and num=?";
		ResultSet rs = null;
		NoticeVO bVo = null;
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, pass);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bVo = getBoardVOFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
		}
		return bVo;
	}

	public void deleteBoard(int num) throws NamingException {
		String sql = "delete from notice where num=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
