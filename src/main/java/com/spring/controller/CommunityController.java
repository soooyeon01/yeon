package com.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.CommunityDTO;
import com.spring.domain.LikeDTO;
import com.spring.domain.MembersDTO;
import com.spring.domain.ReplyDTO;
import com.spring.service.CommunityService;
import com.spring.service.LikeService;
import com.spring.service.LoginService;
import com.spring.service.ReplyService;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Log4j
public class CommunityController {

	private final CommunityService service;
	private final LoginService logservice;
	private final ReplyService rservice;
	private final LikeService lservice;
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * @RequestMapping("/clist") 
	 * public String CommunityList(Model model,Criteria
	 * cri) { log.info(model); PageMaker pageMaker = new PageMaker(cri, 100);
	 * model.addAttribute("communityList",service.getAllCommunityByPage(pageMaker));
	 * model.addAttribute("pageMaker",pageMaker); return "community/community"; }
	 */
	
	@PostMapping("/newR")
	@ResponseBody		
	public Map<String, String> newR(@RequestBody ReplyDTO rdto, HttpSession session) {

		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
        System.out.println("댓글 등록 통신 성공");
        
        Map<String, String> map = new HashMap<>(); 
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");

            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            
            log.info("로긘함. 스크랩 진행");
			
			rservice.registerReply(rdto);
			log.info("댓글 등록 서비스 성공");
			
			map.put("result", "newSuccess");
			
        } else {
        	map.put("result", "fail");
		}
        return map;
	}
	
	@PostMapping("/upR")
	@ResponseBody		
	public Map<String, String> UpdateR(@RequestBody ReplyDTO rdto, HttpSession session) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
        System.out.println("댓글 수정 통신 성공");
        
        Map<String, String> map = new HashMap<>(); 
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            
            log.info("로그인 유지중...");
			
			rservice.modifyReply(rdto);
			log.info("댓글 수정 서비스 성공");
			
			map.put("result", "upSuccess");
			
        } else {
        	map.put("result", "fail");
		}
        return map;
	}
	
	@PostMapping("/delR")
	@ResponseBody		
	public Map<String, String> DeleteR(@RequestBody ReplyDTO rdto, HttpSession session) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
        System.out.println("댓글 삭제 통신 성공");
        
        Map<String, String> map = new HashMap<>(); 
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            
            log.info("로그인 유지중...");
			
			rservice.removeReply(rdto);
			log.info("댓글 삭제 서비스 성공");
			
			map.put("result", "delSuccess");
			
        } else {
        	map.put("result", "fail");
		}
        return map;
	}

	
	// localhost:8080/4jojo/community/replyList/{c_no}
	@GetMapping("/reply/{c_no}")
	@ResponseBody
	public Map<String, Object> getList(@PathVariable int c_no, Model model) {
		log.info("댓글 목록 컨트롤러 동작");
		List<ReplyDTO> list = rservice.getReplyList(c_no);
		List<ReplyDTO> list2 = rservice.getReplyList(c_no);
		log.info("댓글리스트"+list);
		model.addAttribute("list2",list2);
		int total = rservice.cntTotal(c_no);
		model.addAttribute("total",total);
		
		//ModelAndView view = new ModelAndView();
		log.info("댓글 갯수 " + rservice.cntTotal(c_no));
		//view.setViewName("/community/commuSel");
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		
		return map;
	}
	
	@RequestMapping("/clist")
	public String CommunityList(HttpSession session, Model model, LikeDTO ldto) {

		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
         
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            model.addAttribute("viewCntList", service.getViewCntCommunity());
            model.addAttribute("communityList",service.getAllCommunity());
            model.addAttribute("likeCntList", service.getLikeCntCommunity());
            
            //model.addAttribute("likeCnt", lservice.getLikeCnt(ldto));
            return "community/community";
        }else {
        	model.addAttribute("msg", "로그인 후 이용하실 수 있습니다.^~^"); 
			model.addAttribute("url", "/4jojo/main/main"); 
			return "alert";
        }
        
	}
	
	@RequestMapping("/myclist")
	public String MyCommunList(HttpSession session, Model model, String nickname) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");
            String email = (String) session.getAttribute("SESS_EMAIL");
            nickname = (String) session.getAttribute("SESS_NICKNAME");
//          session.setAttribute("id", email);
            model.addAttribute("myCommuList",service.getMyCommunity(nickname));
            return "community/mycommunity";
        }else {
        	return "main/main";
        }
	}
	
	@GetMapping("/newCommu")
	public String moveRegi() {
		return "community/commuRegi";
	}
	
	@RequestMapping("/newCommu")
	public String CommunityRegi(HttpSession session, CommunityDTO commu, MembersDTO mdto) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");

        	String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
//          session.setAttribute("id", email);
            
			int result=service.registerCommunity(commu);
			if(result>0) {
				return "redirect:/community/clist";
			}else {
				return "redirect:/community/commuRegi";
			}
        }else {
        	return "main/main";
        }
    }
	
	@GetMapping("/commuSel")
	public String CommuSel(HttpSession session, Model model, int c_no, LikeDTO ldto) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
         
        if(SESS_AUTH != null && SESS_AUTH) {
	//      request.setCharacterEncoding("utf-8");

	        String email = (String) session.getAttribute("SESS_EMAIL");
	        String nickname = (String) session.getAttribute("SESS_NICKNAME");
	//      session.setAttribute("id", email);
			CommunityDTO selectone=service.getCommunity(c_no);
			model.addAttribute("selectone", selectone);
			service.viewCnt(c_no);
			
			LikeDTO islike=new LikeDTO();
			islike.setC_no(c_no);
			islike.setNickname(nickname);
			log.info("여기1 " + lservice.getLikeCnt(ldto));
			model.addAttribute("islike", lservice.findLike(islike));
			model.addAttribute("getLikeCnt", lservice.getLikeCnt(ldto));
			log.info("여기2 " + lservice.getLikeCnt(ldto));
			
			return "community/commuSel";
        }else {
        	return "main/main";
        }	
	}
	
	@PostMapping("/likeUp")
	@ResponseBody 
	public int likeup(@RequestBody LikeDTO islike) {
		log.info("좋아요 컨트롤러 연결 성공");
		log.info(islike.getC_no());
		log.info(islike.getNickname());
		return lservice.likeUp(islike);
	
	}
	
	@PostMapping("/likeDown")
	@ResponseBody
	public int likeDown(@RequestBody LikeDTO islike) {
		log.info("좋아요 싫어요!");
		return lservice.likeDown(islike);
	}
	
	@PostMapping("/getLikeCnt")
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> getLikeCnt(@RequestBody LikeDTO total) {
	    log.info("총 추천 갯수는? " + total);

	    int likeCnt = lservice.getLikeCnt(total);
	    Map<String, Integer> response = new HashMap<>();
	    response.put("likeCnt", likeCnt);
	    return ResponseEntity.ok(response); // 좋아요 개수가 포함된 JSON 객체로 반환합니다.
	}
	
	
	@RequestMapping("/commuUp1")
	public String CommunityUdt(Model model, int c_no, String nickname) {
		model.addAttribute("selectone",service.getCommunity(c_no));
			return "community/commuUp";
		}
	
	@PostMapping("/commuUp")
	public String Updt(CommunityDTO commu) {
	int	result=service.modifyCommunity(commu);
		if(result>0) {
			return "redirect:/community/commuSel?c_no="+commu.getC_no();
		}else {
			return "redirect:/community/commuUp?c_no="+commu.getC_no();
		}
	}
	
	@RequestMapping("/commuDel")
	public String CommuDel(int c_no) {
	int	result=service.removeCommunity(c_no);
		if(result>0) {
			return "redirect:/community/clist";
		}else {
			return "redirect:/community/commuSel?c_no="+c_no;
		}
	}
	
	@RequestMapping("/Pwd")
	public String pwd() {
		return "community/mypagePwd";	
	}
	// --------------- upload ----------------------
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\STS3\\workspace\\4jojo\\src\\main\\webapp\\resources\\image\\temp";

		String uploadFolderPath = getFolder();
		// make folder --------
		File uploadPath = new File(uploadFolder, uploadFolderPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		// make yyyy/MM/dd folder

		for (MultipartFile multipartFile : uploadFile) {

			AttachFileDTO attachDTO = new AttachFileDTO();

			String uploadFileName = multipartFile.getOriginalFilename();

			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name: " + uploadFileName);
			attachDTO.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);

				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);

				// check image type file
				if (checkImageType(saveFile)) {

					attachDTO.setImage(true);

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);

					thumbnail.close();
				}

				// add to List
				list.add(attachDTO);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} // end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 오늘 날짜의 경로를 문자열로 생성한다.
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date(0);
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("fileName : " + fileName);
		File file = new File("C:\\STS3\\workspace\\4jojo\\src\\main\\webapp\\resources\\image\\temp" + fileName);
		log.info("file:" + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content - Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){
		log.info("download file : " + fileName);
		Resource resource = new FileSystemResource("C:\\STS3\\workspace\\4jojo\\src\\main\\webapp\\resources\\image\\temp" + fileName);
		
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String resourceName = resource.getFilename();
		
		/*
		 * // remove UUID 
		 * String resourceOriginalName =
		 * resourceName.substring(resourceName.indexOf("-")+1); HttpHeaders headers =
		 * new HttpHeaders(); try { String downloadName = null;
		 * if(userAgent.contains("Trident")) { log.info("IE browser");
		 * 
		 * downloadName = URLEncoder.encode(resourceOriginalName,
		 * "UTF-8").replaceAll("\\+", " "); log.info("Edge name: " + downloadName);
		 * }else if(userAgent.contains("Edge")) { log.info("Edge browser"); downloadName
		 * = URLEncoder.encode(resourceOriginalName, "UTF-8");
		 * log.info("Edge name : "+downloadName); }else { log.info("Chrome browser");
		 * downloadName = new String(resourceOriginalName.getBytes("UTF-8"),
		 * "ISO-8859-1"); }
		 */
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = null;
			if(userAgent.contains("Trident")) {
				log.info("IE browser");
				
				downloadName = URLEncoder.encode(resourceName, "UTF-8").replaceAll("\\+", " ");
				log.info("Edge name: " + downloadName);
			}else {
				log.info("Chrome browser");
				downloadName = new String(resourceName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("deleteFile : " + fileName);
		
		File file;
		
		try {
			file = new File("C:\\STS3\\workspace\\4jojo\\src\\main\\webapp\\resources\\image\\temp" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("largetFileName : " + largeFileName);
				file = new File(largeFileName);
				file.delete();
			}
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	// ---------------------------------------------
	
}
