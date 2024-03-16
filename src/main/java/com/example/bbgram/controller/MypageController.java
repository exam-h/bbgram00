package com.example.bbgram.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bbgram.entity.Mypage;
import com.example.bbgram.entity.UserInf;
import com.example.bbgram.form.MypageForm;
import com.example.bbgram.form.UserForm;
import com.example.bbgram.repository.MypageRepository;

@Controller
public class MypageController {

	protected static Logger log = LoggerFactory.getLogger(MypageController.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MypageRepository repository;

	@Autowired
	private HttpServletRequest request;

	@Value("${image.local:false}")
	private String imageLocal;

	@GetMapping(path = "/mypage")
	public String index(Principal principal, Model model) throws IOException {
		Authentication authentication = (Authentication) principal;
		UserInf user = (UserInf) authentication.getPrincipal();

		Iterable<Mypage> mypage = repository.findAllByOrderByUpdatedAtDesc();
		List<MypageForm> list = new ArrayList<>();
		for (Mypage entity : mypage) {
			MypageForm form = getMypage(user, entity);
			list.add(form);
		}
		model.addAttribute("list", list);

		return "mypage/index";
	}

	public MypageForm getMypage(UserInf user, Mypage entity) throws FileNotFoundException, IOException {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.typeMap(Mypage.class, MypageForm.class).addMappings(mapper -> mapper.skip(MypageForm::setUser));

		boolean isImageLocal = false;
		if (imageLocal != null) {
			isImageLocal = new Boolean(imageLocal);
		}
		MypageForm form = modelMapper.map(entity, MypageForm.class);

		if (isImageLocal) {
			try (InputStream is = new FileInputStream(new File(entity.getPath()));
					ByteArrayOutputStream os = new ByteArrayOutputStream()) {
				byte[] indata = new byte[10240 * 16];
				int size;
				while ((size = is.read(indata, 0, indata.length)) > 0) {
					os.write(indata, 0, size);
				}
				StringBuilder data = new StringBuilder();
				data.append("data:");
				data.append(getMimeType(entity.getPath()));
				data.append(";base64,");

				data.append(new String(Base64Utils.encode(os.toByteArray()), "ASCII"));
				form.setImageData(data.toString());
			}
		}

		UserForm userForm = modelMapper.map(entity.getUser(), UserForm.class);
		form.setUser(userForm);

		return form;
	}

	private String getMimeType(String path) {
		String extension = FilenameUtils.getExtension(path);
		String mimeType = "image/";
		switch (extension) {
		case "jpg":
		case "jpeg":
			mimeType += "jpeg";
			break;
		case "png":
			mimeType += "png";
			break;
		case "gif":
			mimeType += "gif";
			break;
		}
		return mimeType;
	}

	@GetMapping(path = "/mypage/pic")
	public String newMypage(Model model) {
		model.addAttribute("form", new MypageForm());
		return "/mypage/pic";
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.POST)
	public String create(Principal principal, @Validated @ModelAttribute("form") MypageForm form, BindingResult result,
			Model model, @RequestParam MultipartFile image, RedirectAttributes redirAttrs)
			throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("hasMessage", true);
			model.addAttribute("class", "alert-danger");
			model.addAttribute("message", "画像の登録に失敗しました。");
			return "/mypage/pic";
		}

		boolean isImageLocal = false;
		if (imageLocal != null) {
			isImageLocal = new Boolean(imageLocal);
		}

		Mypage entity = new Mypage();
		Authentication authentication = (Authentication) principal;
		UserInf user = (UserInf) authentication.getPrincipal();
		entity.setUserId(user.getUserId());
		File destFile = null;
		if (isImageLocal) {
			destFile = saveImageLocal(image, entity);
			entity.setPath(destFile.getAbsolutePath());
		} else {
			entity.setPath("");
		}
		entity.setDescription(form.getDescription());
		repository.saveAndFlush(entity);

		redirAttrs.addFlashAttribute("hasMessage", true);
		redirAttrs.addFlashAttribute("class", "alert-info");
		redirAttrs.addFlashAttribute("message", "写真の登録に成功しました。");

		return "redirect:/mypage";
	}

	private File saveImageLocal(MultipartFile image, Mypage entity) throws IOException {
		File uploadDir = new File("/uploads");
		uploadDir.mkdir();

		String uploadsDir = "/uploads/";
		String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);
		if (!new File(realPathToUploads).exists()) {
			new File(realPathToUploads).mkdir();
		}
		String fileName = image.getOriginalFilename();
		File destFile = new File(realPathToUploads, fileName);
		image.transferTo(destFile);

		return destFile;
	}

}