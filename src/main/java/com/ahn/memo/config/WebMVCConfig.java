package com.ahn.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ahn.memo.common.FileManagerService;
import com.ahn.memo.common.PermissionInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	@Autowired
	private PermissionInterceptor interception;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH + "/");
		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interception)
		.addPathPatterns("/**")	// 인터셉터를 거쳐서 처리할 페이지 의 url 규칙
		.excludePathPatterns("/user/signout", "/static/**", "/image/**");
	}
	
}
