package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value={"/upload/*", "/display/*"})
@Log4j
public class UploadController {
	private String uploadFolder = "C:\\STS3\\workspace\\4jojo\\src\\main\\webapp\\resources\\image\\temp";
	
	
	@GetMapping("/uploadForm")
	public String uploadForm() {
	   log.info("upload form");
	   return "upload/upload-form";
	}
	
	@PostMapping("/uploadFormAction")
	   public String uploadFormPost(@RequestParam("uploadFile") MultipartFile[] uploadFile, Model model) {
	      log.info("uploadFormAction");
	     
	      for(MultipartFile multipartFile : uploadFile) {
	         log.info("----------------------------");
	         log.info("upload File Name : " + multipartFile.getOriginalFilename() );
	         log.info("upload File Size : " + multipartFile.getSize() );
	         
	         File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
	         
	         try {
	            multipartFile.transferTo(saveFile);
	            
	            model.addAttribute("msg", "success file uploaded-" + multipartFile.getOriginalFilename());
	            model.addAttribute("originalFileName", multipartFile.getOriginalFilename());
	            
	         } catch (Exception e) {
	            log.error(e.getMessage());
	            model.addAttribute("msg", "fail file uploaded-" + multipartFile.getOriginalFilename());
	         }
	         
	      }
	      return "upload/upload-action";
	   }
	
	@GetMapping("/attach")
	   @ResponseBody
	   public ResponseEntity<byte[]> getFile(String fileName){   
	       File file=new File(uploadFolder+File.separatorChar,fileName);
	       ResponseEntity<byte[]> result=null;
	       try {
	           HttpHeaders headers=new HttpHeaders();
	           headers.add("Content-Type", Files.probeContentType(file.toPath()));
	           result=new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK );
	       }catch (IOException e) {
	           e.printStackTrace();
	       }
	       
	       return result;
	   }
	   
	   @GetMapping("/uploadAjax")
	   public String uploadAjax() {
	      
	      log.info("uploadAjax");
	      return "upload/upload-ajax";
	   } 
	   
	   @PostMapping("/uploadAjaxAction")
	   @ResponseBody
	   public String uploadAjaxAction(MultipartFile[] uploadFile, HttpServletRequest request) {
	      log.info("uploadFormAction");
	      
	      String srcPath = request.getContextPath();
	      srcPath += "/display/attach?fileName=";
	      
	      JsonArray jsonArray = new JsonArray();
	      for(MultipartFile multipartFile : uploadFile) {
	         log.info("----------------------------");
	         log.info("upload File Name : " + multipartFile.getOriginalFilename() );
	         log.info("upload File Size : " + multipartFile.getSize() );
	         
	         File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
	         JsonObject jsonObject = new JsonObject();
	         try {
	            multipartFile.transferTo(saveFile);
	            jsonObject.addProperty("msg", "success file uploaded-" + multipartFile.getOriginalFilename());
	            jsonObject.addProperty("src", srcPath +  multipartFile.getOriginalFilename());
	            
	         } catch (Exception e) {
	            log.error(e.getMessage());
	            jsonObject.addProperty("msg", "fail file uploaded-" + multipartFile.getOriginalFilename());
	         }
	         jsonArray.add(jsonObject);
	      }
	      return new Gson().toJson(jsonArray);
	   }
	
}
