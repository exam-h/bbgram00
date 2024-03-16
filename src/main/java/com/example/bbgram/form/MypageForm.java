package com.example.bbgram.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

	@NotEmpty
	@Size(max = 100)
	private String description;

	private UserForm user;

}