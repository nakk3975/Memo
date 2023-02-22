package com.ahn.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ahn.memo.common.FileManagerService;
import com.ahn.memo.post.dao.PostDAO;
import com.ahn.memo.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String title, String content, MultipartFile file) {
		
		String imagePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, title, content, imagePath);
	}
	
	public List<Post> selectPostList(int userId) {
		return postDAO.selectList(userId);
	}
	
	public Post getPost(int postId) {
		return postDAO.selectPost(postId);
	}
	
	public int deletePost(int postId) {
		return postDAO.deletePost(postId);
	}
	
	public int updatePost(int postId, String title, String content) {
		return postDAO.updatePost(postId, title, content);
	}

}
