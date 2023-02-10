package com.springlab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springlab.member.MemberDTO;
import com.springlab.member.MemberDAO;
import com.springlab.member.MemberDTO;
import com.springlab.member.MemberDAO;
import com.springlab.member.MemberDTO;


//@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DispatcherServlet() {
        super();
  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	//doGet, doPost 메소드에서 받는 내용을 처리하는 메소드
	public void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트의 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
			System.out.println("uri : " +uri);
			
		String path = uri.substring(uri.lastIndexOf("/"));
			System.out.println("path : " +path);
			
		//2.클라이언트의 요청 정보에 따라 분기 처리
		
		if (path.equals("/login.do")) {
			
			System.out.println("사용자 정보 처리");
			System.out.println("/login.do 요청을 보냈습니다.");
			
			//1. 클라이언트에서 보내는 변수 값을 받아서 변수에 저장
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			
			System.out.println("폼에서 넘긴 변수 id값 출력 : " +id);
			System.out.println("폼에서 넘긴 변수 pass값 출력 : " +pass);
			
			//2. 클라이언트에서 넘긴 변수값을 받아서 저장된 변수를 DTO에 Setter 주입
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPass(pass);
			
			//3. 비즈니스 로직을 처리하는 인터페이스에 dto를 주입해서 비즈니스 로직을 처리
			
			MemberDAO member = new MemberDAO();
			
			
			//DB의 클라이언트에서 넘긴 ID와 Pass를 select해서 그 값을 DTO에 담아서 리턴
			
			
			//4. 백엔드의 로직을 모두 처리 후 View 페이지로 이동
			if (member.loginMember(dto)) {
				response.sendRedirect("getMemberList.do");
				System.out.println("아이디와 패스워드 일치");
			}else {
				response.sendRedirect("login.jsp");
				System.out.println("아이디와 패스워드 불일치");
			}
			
			
		}else if (path.equals("/getMemberList.do")) {
			System.out.println("회원 정보 출력");	
			
			//1. Client로부터 /getMemberList.do 요청을 받음. (회원 정보를 출력해 달라고 요청)
			
			//2. 비즈니스 로직 처리
			//MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			//memberList에는 DB에서 쿼리한 레코드를 담은 DTO 객체가 내장되어있다.
			//List<MemberDTO> memberList = dao.getMemberList(dto);
			
			List<MemberDTO> memberList_disp = dao.getMemberList();
			//3. 클라이언트에게 memberList를 전달해야 한다.
			HttpSession session = request.getSession();
			session.setAttribute("memberList_s", memberList_disp);
			
			/*
			 * HttpSession session = request.getSession();
			 * session.setAttribute("memberList_s", dao.getMemberList());
			 */
			
			//4. 뷰페이지로 이동
			response.sendRedirect("getMemberList.jsp");
			
		
			
		}else if (path.equals("/getMember.do")) {
			
			System.out.println("회원 상세 내용보기");	
			
			//1. 클라이언트의 넘긴 변수 값 받기 ("idx")
			String idx = request.getParameter("idx");
			System.out.println("idx 변수값 : " + idx);
			
			//2. 비즈니스 로직 처리
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			//클라이언트에게 받은 값을 dto에 setter 주입
			dto.setIdx(Integer.parseInt(idx));
			
			//리턴을 받아온다.
			MemberDTO member = dao.getMember(dto);
			
			//CNT를 1씩 상승
			dao.viewCount(dto);
			
			//DB의 값이 저장된 DTO (board)를 session 변수에 할당해서 뷰 페이지로 전달
			HttpSession session = request.getSession();
			
			session.setAttribute("member", member);
			
			//3. 뷰 페이지로 전달
			response.sendRedirect("getMember.jsp");
			
		}else if (path.equals("/insertMember.do")) {
			
			System.out.println("member 테이블에 값을 저장");	
			//1. 클라이언트에서 넘어오는 변수 값을 받아서 새로운 변수에 저장
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String age = request.getParameter("age");
			String weight = request.getParameter("weight");
			
			//2. 비즈니스 로직 처리
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			//dto의 setter 메소드 호출시 클라이언트에게 넘어오는 변수를 할당.
			dto.setId(id);
			dto.setPass(pass);
			dto.setName(name);
			dto.setEmail(email);
			dto.setAge(Integer.parseInt(age));
			dto.setWeight(Integer.parseInt(weight));
			
			dao.insertMember(dto);
			
			//3. view 페이지 전송
			response.sendRedirect("getMemberList.do");
			
			
		}else if (path.equals("/updateMember.do")) {
			System.out.println("회원정보 수정 처리");
			
			//1. 클라이언트에서 넘어오는 변수를 받음.
			String pass = request.getParameter("pass");
			String name = request.getParameter("name");
			String idx = request.getParameter("idx");
			
			//2. DTO, DAO 객체를 사용해서 비즈니스 로직 처리
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			dto.setIdx(Integer.parseInt(idx));
			dto.setPass(pass);
			dto.setName(name);
			
			dao.updateMember(dto);
			
			//3. 백엔드의 로직을 모두 처리 후 client에게 View페이지로 이동
			response.sendRedirect("getMemberList.do");
			
			
		}else if (path.equals("/deleteMember.do")) {
			System.out.println("회원 삭제 처리");	
			
			//1. 클라이언트에서 넘긴 idx를 받아서 변수에 저장함.
			String idx = request.getParameter("idx");
			
			//2. DTO, DAO에 로직 처리 (백엔드의 비즈니스 로직 처리)
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			dto.setIdx(Integer.parseInt(idx));
		
			dao.deleteMember(dto);
			
			//3. 비즈니스 로직 처리 완료 후 View 페이지로 이동
			response.sendRedirect("getMemberList.do");
			
		}else if (path.equals("/logout.do")) {
			
			System.out.println("사용자 로그아웃 처리");
		}
		
		
	}
	
	
	
	
	
	
	
	

}
