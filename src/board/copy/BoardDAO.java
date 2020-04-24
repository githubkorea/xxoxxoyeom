package board.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import login.LoginVO;

public class BoardDAO {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {
		String url = "jdbc:mysql://projectdb.cdiwkaggifue.ap-northeast-2.rds.amazonaws.com:3306/projectdb?serverTimezone=UTC";
		String user = "javadb";
		String password = "12345";
		Connection con = null;
	
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	public int write(BoardVO vo) {
		// 게시글 작성후 게시하기를 눌렀을때, 게시글의 글내용을 board에 저장
		String sql = "insert into board(contentname,content,writer,writedate,viewcount) values(?,?,?,now(),0)";
		int result=0;
		
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, vo.getContentname());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());

			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
		}
		
		return result;
		
	}
	
	
	
	public BoardVO view(int boardno) {
		// 게시글을 클릭 하였을때, 게시글의 내용을 보여줌 boardno로 검색
		
		String sql = "select contentname,content,boardno from board where boardno=?";
		
		BoardVO vo = null;
		try(Connection con= getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, boardno);
			
			//select
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo=new BoardVO();
			vo.setContentname(rs.getString("contentname"));
			vo.setContent(rs.getString("content"));
			vo.setBoardno(rs.getInt("boardno"));
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	// 글 수정
	// 작성자id와 일치 할 경우 글 수정메뉴 사용 가능하도록 (DAO에서 말고)
	public int modify(BoardVO vo) {
		int result=0;
		String sql = "call modify(?,?,?)";
		try(Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1,vo.getContentname());
			pstmt.setString(2,vo.getContent());
			pstmt.setInt(3, vo.getBoardno());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		}
		
		
		return result;
	}
	
	// 글삭제
		// 글 수정메뉴 안의 메뉴에 같이 나타내기
		public int delete(int no) {
			int result=0;
			String sql = "delete from board where boardno=?";
			
			try(Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
	
	
	
		
		
	// 게시판에 글 나타내기
	// 작성된 글 내림차순으로 나타내기(boardno 기준)

	public Vector<BoardVO> ViewBoard (){
		// 
		
		String sql = "select contentname,writer,boardno,writedate,viewcount from board order by boardno desc";
//		String sql = "select contentname,writer,boardno,writedate,viewcount from board order by boardno desc";
		
		Vector<BoardVO> list = new Vector<BoardVO>();
		
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setContentname(rs.getString("contentname"));
				vo.setWriter(rs.getString("writer"));
				vo.setBoardno(rs.getInt("boardno"));
				vo.setWritedate(rs.getString("writedate"));
				vo.setViewcount(rs.getInt("viewcount"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	public Vector<BoardVO> Searchcontentname (String s){
		
		String sql = "select contentname,writer,boardno,writedate,viewcount from board where contentname like ? order by boardno desc";
		Vector<BoardVO> list = new Vector<BoardVO>();
		
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, "%"+s+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setContentname(rs.getString("contentname"));
				vo.setWriter(rs.getString("writer"));
				vo.setBoardno(rs.getInt("boardno"));
				vo.setWritedate(rs.getString("writedate"));
				vo.setViewcount(rs.getInt("viewcount"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Vector<BoardVO> Searchwriter (String s){
		
		String sql = "select contentname,writer,boardno,writedate,viewcount from board where writer like ? order by boardno desc";
		Vector<BoardVO> list = new Vector<BoardVO>();
		
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, "%"+s+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setContentname(rs.getString("contentname"));
				vo.setWriter(rs.getString("writer"));
				vo.setBoardno(rs.getInt("boardno"));
				vo.setWritedate(rs.getString("writedate"));
				vo.setViewcount(rs.getInt("viewcount"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Vector<BoardVO> Searchcontent (String s){
		
		String sql = "select contentname,writer,boardno,writedate,viewcount from board where content like ? order by boardno desc";
		Vector<BoardVO> list = new Vector<BoardVO>();
		
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, "%"+s+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setContentname(rs.getString("contentname"));
				vo.setWriter(rs.getString("writer"));
				vo.setBoardno(rs.getInt("boardno"));
				vo.setWritedate(rs.getString("writedate"));
				vo.setViewcount(rs.getInt("viewcount"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void viewcount(int view,int boardno) {
		
			String sql = "update board set viewcount= "+view+" where boardno = ?";
			try(Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setInt(1,boardno);
				
				pstmt.executeUpdate();
			} catch (Exception e) {
			}
		
	}
	
}
