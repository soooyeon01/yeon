package com.java.servlet.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.servlet.dao.MypageDAO;
import com.java.servlet.dao.impl.MypageDAOImpl;
import com.java.servlet.service.MypageService;
import com.java.servlet.util.PageMaker;
import com.java.servlet.vo.CommunityVO;
import com.java.servlet.vo.MembersVO;

public class MypageServiceImpl implements MypageService {

	private static MypageService instance = new MypageServiceImpl();
	private final MypageDAO dao = MypageDAOImpl.getInstance();
	
	private MypageServiceImpl() { }
	
	public static MypageService getInstance() {
		
		return instance;
	}

	@Override
	public MembersVO getMypage(String email) {
		return dao.selectMypage(email);

	}
	
	}
//	@Override
//	public int modifyPwd(MembersVO vo) {
//		return dao.updatePwd(vo);
//	}
//
//	@Override
//	public MembersVO readMember(String email) {
//		// TODO Auto-generated method stub
//		return dao.selectMember(email);
//	}
//
//	@Override
//	public int removeId(String email) {
//		// TODO Auto-generated method stub
//		return dao.deleteId(email);
//	}


	
//	public void execute(HttpServletRequest request, HttpServletResponse reponse) {
//		String pwd=request.getParameter("pwd");
//		String uppwd=request.getParameter("uppwd");
//		MypageDAO dao=MypageDAOImpl.getInstance();
//		dao.updatePwd();
//	}
	
