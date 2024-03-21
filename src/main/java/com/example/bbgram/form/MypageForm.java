package com.example.bbgram.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.bbgram.validation.constraints.ImageByte;
import com.example.bbgram.validation.constraints.ImageNotEmpty;

import lombok.Data;

@Data
public class MypageForm {

	private Long id;

	private Long userId;

	@ImageNotEmpty
	@ImageByte(max = 2000000)
	private MultipartFile image;

	private String imageData;

	private String path;

	private UserForm user;

}