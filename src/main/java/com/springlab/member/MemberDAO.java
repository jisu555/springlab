package com.springlab.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springlab.common.JDBCUtil;

@Repository("memberDAO")
public class MemberDAO {
	
	//1.JDBC 관련 변수 선언 (Connection, PreparedStatement(Statement), ResultSet)
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//2. SQL  쿼리를 담은 상수를 선언 
	private final String MEMBER_INSERT = "insert into member (idx, id, pass, name, email, age, weight) values( (select nvl(max(idx),0)+1 from member),?,?,?,?,?,?)";
	private final String MEMBER_UPDATE = "update member set pass=?, name=? where idx=?";
	private final String MEMBER_DELETE = "delete member where idx=?";
	private final String MEMBER_GET = "select * from member where idx=?";		//DataBase의 테이블에서 1개의 레코드만 출력 (상세보기)
	private final String MEMBER_LIST = "select * from member order by idx desc";		//DataBase의 테이블의 여러개의 레코드를 LIST (ArrayList() ) 
	private final String MEMBER_LOGIN ="select * from member where id=? and pass=?";
	private final String VIEW_COUNT = "update member set cnt=((select cnt from member where idx=?)+1) where idx=?";
	
	//3. 메소드
	
	//3-1. 글 등록 처리 메소드 : insertMember()
	public void insertMember(MemberDTO dto) {
		System.out.println("==> JDBC로 insertMember() 기능처리 - 시작");
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			pstmt = conn.prepareStatement(MEMBER_INSERT);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setInt(5, dto.getAge());
			pstmt.setDouble(6, dto.getWeight());
			
			pstmt.executeUpdate();
			
			System.out.println("==> JDBC로 insertMember() 기능처리 - 완료");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 insertMember() 기능처리 - 실패");
		}finally {
			JDBCUtil.close(pstmt, conn);
			System.out.println("모든 객체가 close() 되었습니다.");
		}

	}
	
	
	//3-2. 멤버 조회 처리 메소드 : getMember() : 레코드 1개를 DB에서 select해서 DTO 객체에 담아서 리턴
	
	public MemberDTO getMember(MemberDTO dto) {
		System.out.println("==> JDBC로 getMember() 기능처리 - 시작");
		
		//리턴으로 돌려줄 변수 선언 : try 블락 밖에서 선언
		MemberDTO member = new MemberDTO();
		try {
			//객체 생성 : : Connection, PreparedStatement
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_GET);
			pstmt.setInt(1, dto.getIdx());
			
			//DB를 select한 결과를 rs에 저장함.
			rs = pstmt.executeQuery();
			
			//rs에 담긴 값을 DTO (member)에 저장해서 리턴으로 돌려줌
			
			if(rs.next()) {
				member.setIdx(rs.getInt("IDX"));
				member.setId(rs.getString("ID"));
				member.setPass(rs.getString("PASS"));
				member.setName(rs.getString("NAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAge(rs.getInt("AGE"));
				member.setWeight(rs.getDouble("WEIGHT"));
				member.setRegDate(rs.getDate("REGDATE"));
				member.setCnt(rs.getInt("CNT"));
				
			}else {
				System.out.println("레코드의 결과가 없습니다.");
			}
			
			System.out.println("==> JDBC로 getBoard() 기능처리 - 완료");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 getBoard() 기능처리 - 실패");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//
	public Boolean loginMember(MemberDTO dto) {
		try {
			conn=JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_LOGIN);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("JDBC로 쿼리 실행 중 오류발생");
		
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}

		return false;
	}
	
	
	//회원목록 조회 처리 메소드 : getBoardList()
	//public List<MemberDTO> getMemberList(MemberDTO dto)
	public List<MemberDTO> getMemberList() {
		System.out.println("==> JDBC로 getMemberList() 기능처리 - 시작");
		
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		MemberDTO member=null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_LIST);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					member = new MemberDTO();
					
					member.setIdx(rs.getInt("IDX"));
					member.setId(rs.getString("ID"));
					member.setPass(rs.getString("PASS"));
					member.setName(rs.getString("NAME"));
					member.setEmail(rs.getString("EMAIL"));
					member.setAge(rs.getInt("AGE"));
					member.setWeight(rs.getDouble("WEIGHT"));
					member.setRegDate(rs.getDate("REGDATE"));
					member.setCnt(rs.getInt("CNT"));
					
					memberList.add(member);
					
				}while (rs.next());
			
			}else {
				System.out.println("테이블에 레코드가 비어있습니다.");
			}
			
			System.out.println("==> JDBC로 getBoardList() 기능처리 - 완료");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 getBoardList() 기능처리 - 실패");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return memberList;
	}
	
	//글 수정 처리 메소드 : updateMember()
	public void updateMember(MemberDTO dto) {
		System.out.println("==> JDBC로 updateBoard() 기능처리 -  시작");
		
		try {
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_UPDATE);
			
			pstmt.setString(1, dto.getPass());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getIdx());
			
			pstmt.executeUpdate();
			
			System.out.println("==> JDBC로 updateBoard() 기능처리 -  완료");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 updateBoard() 기능처리 -  실패");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	//글 삭제 처리 메소드 : deleteMember()
	public void deleteMember(MemberDTO dto) {
		System.out.println("==> JDBC로 deleteBoard() 기능처리 - 시작");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_DELETE);
			pstmt.setInt(1, dto.getIdx());
			
			pstmt.executeUpdate();
			
			System.out.println("==> JDBC로 deleteBoard() 기능처리 - 완료");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 deleteBoard() 기능처리 - 실패");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	public void viewCount(MemberDTO dto) {
		conn = JDBCUtil.getConnection();
		try {
			pstmt=conn.prepareStatement(VIEW_COUNT);
			pstmt.setInt(1, dto.getIdx());
			pstmt.setInt(2, dto.getIdx());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
