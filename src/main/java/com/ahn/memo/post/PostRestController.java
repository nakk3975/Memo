package com.ahn.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ahn.memo.post.bo.PostBO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, String> postCreate(
			@RequestParam("title") String title
			, @RequestParam("content") String content
			, @RequestParam(value="file", required=false) MultipartFile file
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		Map<String, String> result = new HashMap<>();
		
		if(session == null || !(session.getAttribute("userId") instanceof Integer)) {
			result.put("result", "fail");
			return result;
		}
		
		int userId = (Integer) session.getAttribute("userId");
		int count = postBO.addPost(userId, title, content, file);
		
		result.put("result", count == 1 ? "success" : "fail");
		return result;
	}
	
	@GetMapping("/delete")
	public Map<String, String> postDelete(
			@RequestParam("id") int postId
			, HttpServletRequest request) {
		
		Map<String, String> result = new HashMap<>();
		HttpSession session = request.getSession(false);
		
		if(session == null || !(session.getAttribute("userId") instanceof Integer)) {
			result.put("result", "fail");
			return result;
		}
		
		int userId = (Integer) session.getAttribute("userId");
		int count = postBO.deletePost(postId, userId);
		
		result.put("result", count == 1 ? "success" : "fail");
		return result;
	}
	
	@PostMapping("/update")
	public Map<String, String> modifyMemo(
			@RequestParam("id") int postId
			, @RequestParam("title") String title
			, @RequestParam("content") String content
			, HttpServletRequest request) {
		
		Map<String, String> result = new HashMap<>();
		HttpSession session = request.getSession(false);
		
		if(session == null || !(session.getAttribute("userId") instanceof Integer)) {
			result.put("result", "fail");
			return result;
		}
		
		int userId = (Integer) session.getAttribute("userId");
		int count = postBO.updatePost(postId, userId, title, content);
		
		result.put("result", count == 1 ? "success" : "fail");
		return result;
	}
	
}
