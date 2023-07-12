package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.MypageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
	
	private final MypageMapper mapper;

//
//	@Override
//	public List<MembersDTO> getMypage(String email) {
//		List<MembersDTO> result = mapper.selectMypage(email);
//		return result;
//	}
	
	//업데이트도 추가해야함
	@Override
	public int modifyMember(MembersDTO mdto) {
		int result = mapper.UpdateMember(mdto);
		return result;
	}

	@Override
	public List<MembersDTO> getMypage() {
		List<MembersDTO> result = mapper.selectMypage();
		return result;
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
	
