package com.spring.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.FindPwdMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindPwdServiceImpl implements FindPwdService {
	
	private final FindPwdMapper mapper;

//	@Override
//	public void findPwd(MembersDTO mdto) {
//		String result = mapper.findPwd(mdto);
//	}
	
	 @Override
	    public int updatePwd(MembersDTO mdto) {
	        return mapper.updatePwd(mdto);
	    }
	 

	//비밀번호 찾기 이메일발송
	 @Override
	    public void sendEmail(MembersDTO mdto, String div) {
	        String charSet = "utf-8";
	        String hostSMTP = "smtp.naver.com";
	        String hostSMTPid = "2qiuo@naver.com";
	        String hostSMTPpwd = "Yesol1101@";

	        String fromEmail = "2qiuo@naver.com";
	        String fromName = "오키도키요";
	        String subject = "";
	        String msg = "";

	        if(div.equals("findpw")) {
	            // 이메일 제목
	            subject = "임시 비밀번호 발급 안내";
	            
	            // 이메일 내용
	            msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
	            msg += "<h3 style='color: blue;'>";
	            msg += mdto.getEmail() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
	            msg += "<p>임시 비밀번호 : ";
	            msg += mdto.getTempPwd() + "</p></div>";
	        }

	        // 이메일을 발송할 수신자 정보
	        String mail = mdto.getEmail();
	        try {
	            HtmlEmail email = new HtmlEmail();
	            email.setDebug(true);
	            email.setCharset(charSet);
	            email.setSSL(true);
	            email.setHostName(hostSMTP);
	            email.setSmtpPort(465); // 587으로 설정 시 오류 발생
	            email.setAuthentication(hostSMTPid, hostSMTPpwd);
	            email.setTLS(true);
	            email.addTo(mail, charSet);
	            email.setFrom(fromEmail, fromName, charSet);
	            email.setSubject(subject);
	            email.setHtmlMsg(msg);
	            email.send();
	        } catch (Exception e) {
	            System.out.println("메일발송 실패 : " + e);
	        }
	    }
	

	 //비밀번호찾기
	 @Override
	    public void findPwd(HttpServletResponse response, MembersDTO mdto) throws IOException {
	        response.setContentType("text/html;charset=utf-8");
	        MembersDTO ck = new MembersDTO();
	        PrintWriter out = response.getWriter();
	        
	     // 가입된 이름과 이메일이 없으면 
	        if(mdao.nameCheck(mdto.getName()) == null || mdao.emailCheck(mdto.getEmail()) == null) {
	          out.print("등록되지 않은 이름 또는 이메일입니다.");
	          out.close();
	        }
	        // 가입한 이름과 이메일이 존재하면
	        else {
	          MembersDTO member = mdao.getMemberByNameAndEmail(mdto.getName(), mdto.getEmail());
	          // 핸드폰 번호와 일치하지 않으면
	          if (member.getPhone() != mdto.getPhone()) {
	            out.print("등록되지 않은 핸드폰 번호입니다.");
	            out.close();
	          }
	        else {
	            // 임시 비밀번호 생성
	            String tempPwd = "";
	            for (int i = 0; i < 12; i++) {
	                tempPwd += (char) ((Math.random() * 26) + 97);
	            }
	            mdto.setTempPwd(tempPwd);
	            
	            // DB에 임시 비밀번호 저장
	            updatePwd(mdto);
	            
	            // 이메일로 임시 비밀번호 발송
	            sendEmail(mdto, "findpw");

	            out.print("이메일로 임시 비밀번호를 발송하였습니다.");
	            out.close();
	        }
	    }

	 mdao.idCheck(vo.getId()) == null





	@Override
	public void findPwd(MembersDTO mdto) {
		// TODO Auto-generated method stub
		
	}

	 
	 
}
