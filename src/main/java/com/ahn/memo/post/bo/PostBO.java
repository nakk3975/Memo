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
	
	// 글 쓰기
	public int addPost(int userId, String title, String content, MultipartFile file) {
		String imagePath = null;
		// 파일 저장
		if(file != null && !file.isEmpty()) {
			imagePath = FileManagerService.saveFile(userId, file);	
		}
		
		return postDAO.insertPost(userId, title, content, imagePath);
	}
	
	// 전체 리스트 불러오기
	public List<Post> selectPostList(int userId) {
		return postDAO.selectList(userId);
	}
	
	public Post getPost(int postId, int userId) {
		return postDAO.selectPost(postId, userId);
	}
	
	public int deletePost(int postId, int userId) {
		Post post = postDAO.selectPost(postId, userId);
		
		if(post == null) {
			return 0;
		}
		
		if(post.getImagePath() != null) {
			FileManagerService.removeFile(post.getImagePath());
		}
		return postDAO.deletePost(postId, userId);
	}
	
	public int updatePost(int postId, int userId, String title, String content) {
		return postDAO.updatePost(postId, userId, title, content);
	}

}
