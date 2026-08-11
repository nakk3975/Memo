package com.ahn.memo.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahn.memo.post.bo.PostBO;
import com.ahn.memo.post.model.Post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostBO postBO;
	
	@GetMapping("/list/view")
	public String listView(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null || !(session.getAttribute("userId") instanceof Integer)) {
			return "redirect:/user/signin/view";
		}
		
		int userId = (Integer) session.getAttribute("userId");
		List<Post> postList = postBO.selectPostList(userId);
		model.addAttribute("posts", postList);
		
		return "post/list";
	}
	
	@GetMapping("/create/view")
	public String createView() {
		return "post/create";
	}
	
	@GetMapping("/detail/view")
	public String detailView(
			@RequestParam("postId") int postId
			, Model model
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if(session == null || !(session.getAttribute("userId") instanceof Integer)) {
			return "redirect:/user/signin/view";
		}
		
		int userId = (Integer) session.getAttribute("userId");
		Post post = postBO.getPost(postId, userId);
		
		if(post == null) {
			return "redirect:/post/list/view";
		}
		
		model.addAttribute("post", post);
		return "post/detail";
	}
}
