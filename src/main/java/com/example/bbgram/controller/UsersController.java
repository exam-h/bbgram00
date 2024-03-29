package com.example.bbgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bbgram.entity.User;
import com.example.bbgram.entity.User.Authority;
import com.example.bbgram.form.UserForm;
import com.example.bbgram.repository.UserRepository;

@Controller
public class UsersController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	@GetMapping(path = "/users/new")
	public String newUser(Model model) {
		model.addAttribute("form", new UserForm());
		return "users/new";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute("form") UserForm form, BindingResult result, Model model,
			RedirectAttributes redirAttrs) {
		String username = form.getUsername();
		String password = form.getPassword();
		String passwordConfirmation = form.getPasswordConfirmation();
		String tel = form.getTel();
		String name = form.getName();
		String age = form.getAge();
		String birthDate = form.getBirthDate();
		String prefecture = form.getPrefecture();
		String city = form.getCity();
		String experience = form.getExperience();
		String position = form.getPosition();
		String throwing = form.getThrowing();  
		String batting = form.getBatting();
		String introduction = form.getIntroduction();

		if (repository.findByUsername(username) != null) {
			FieldError fieldError = new FieldError(result.getObjectName(), "email", "その E メールはすでに使用されています。");
			result.addError(fieldError);
		}
		if (result.hasErrors()) {
			model.addAttribute("hasMessage", true);
			model.addAttribute("class", "alert-danger");
			model.addAttribute("message", "ユーザー登録に失敗しました。");

			return "users/new";
		}

		User entity = new User(username, passwordEncoder.encode(password), Authority.ROLE_USER, 
			tel, name,  age, birthDate, prefecture, city, experience, position, throwing, batting, introduction);
		repository.saveAndFlush(entity);

		return "redirect:/mypage";
	}
	
}

