package com.example.bbgram.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bbgram.entity.Team;
import com.example.bbgram.entity.Teampage;
import com.example.bbgram.form.TeamForm;
import com.example.bbgram.form.TeampageForm;
import com.example.bbgram.repository.TeamRepository;
import com.example.bbgram.repository.TeampageRepository;

@Controller
public class TeampageController {

	protected static Logger log = LoggerFactory.getLogger(MypageController.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TeamRepository Teamrepository;

	@Autowired
	private TeampageRepository repository;
	
	@Autowired
	private HttpServletRequest request;

	@Value("${image.local:false}")
	private String imageLocal;

	@GetMapping(path = "/teampage/{id}")
	public String index(Principal principal,@PathVariable("id") String id, Model model) throws IOException {
		Authentication authentication = (Authentication) principal;
		//TeamInf team = (TeamInf) authentication.getPrincipal();
		Team team = Teamrepository.findById(Long.parseLong(id)).get();
		List<Teampage> teampage = (List<Teampage>)repository.findByTeamIdOrderByUpdatedAtDesc(Long.parseLong(id));
		
		TeampageForm form = null;
		if(teampage.size() != 0){//if(list.size() !== 0){
			Teampage image = teampage.get(0);
			form = getTeampage(image);
		}
		TeamForm teamform = new TeamForm();
		teamform.setTeamId("" + team.getTeamId());
		teamform.setName(team.getName());
		teamform.setRead(team.getRead());
		teamform.setPrefecture(team.getPrefecture());
		teamform.setCity(team.getCity());
		teamform.setExperience(team.getExperience());
		teamform.setFormation(team.getFormation());
		teamform.setFrequency(team.getFrequency());
		teamform.setActivityDays(team.getActivityDays());	
		teamform.setMatchDays(team.getMatchDays());
		teamform.setTeamIntroduction(team.getTeamIntroduction());
		model.addAttribute("teamform", teamform);
		model.addAttribute("form", form);

		return "teampage/index";
	}

	public TeampageForm getTeampage(Teampage entity) throws FileNotFoundException, IOException {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.typeMap(Teampage.class, TeampageForm.class).addMappings(mapper -> mapper.skip(TeampageForm::setId));

		boolean isImageLocal = false;
		if (imageLocal != null) {
			isImageLocal = new Boolean(imageLocal);
		}
		TeampageForm form = modelMapper.map(entity, TeampageForm.class);

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

//		TeamForm userForm = modelMapper.map(entity.getTeam(), TeamForm.class);
//		form.setUser(userForm);

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

	@GetMapping(path = "/teampage/{teamId}/pic")
	public String newMypage(@PathVariable("teamId") Long teamId, Model model) {
		model.addAttribute("form", new TeampageForm());
		model.addAttribute("teamId", teamId);
		return "teampage/pic";
	}

	@RequestMapping(value = "/teampage", method = RequestMethod.POST)
	public String create(Principal principal, @Validated @ModelAttribute("form") TeampageForm form, BindingResult result,
			Model model,@RequestParam("id") Long id, @RequestParam MultipartFile image, RedirectAttributes redirAttrs)
			throws IOException {
 		if (result.hasErrors()) {
			model.addAttribute("hasMessage", true);
			model.addAttribute("class", "alert-danger");
			model.addAttribute("message", "画像の登録に失敗しました。");
			return "teampage/pic";
		}

		boolean isImageLocal = false;
		if (imageLocal != null) {
			isImageLocal = new Boolean(imageLocal);
		}

		Teampage entity = new Teampage();
		Authentication authentication = (Authentication) principal;
//		TeamInf team = (TeamInf) authentication.getPrincipal();
		//entity.setUserId(user.getUserId());
		File destFile = null;
		if (isImageLocal) {
			destFile = saveImageLocal(image, entity);
			entity.setPath(destFile.getAbsolutePath());
		} else {
			entity.setPath("");
		}
		entity.setTeamId(id);
		repository.saveAndFlush(entity);

		redirAttrs.addFlashAttribute("hasMessage", true);
		redirAttrs.addFlashAttribute("class", "alert-info");
		redirAttrs.addFlashAttribute("message", "写真の登録に成功しました。");

		return "redirect:/teampage/" + id;
	}

	private File saveImageLocal(MultipartFile image, Teampage entity) throws IOException {
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