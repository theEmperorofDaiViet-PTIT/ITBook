package jersey.controller;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jersey.dao.AccountDAO;
import jersey.form.RegistrationForm;
import jersey.validator.RegistrationValidator;

@Controller
@Transactional 
@RequestMapping("/account/customer/register")
public class RegistrationController {
	
	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private RegistrationValidator customerRegistrationValidator;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == RegistrationForm.class) {
			dataBinder.setValidator(customerRegistrationValidator); 
		}
	}
	
	@GetMapping
	public String registerForm(Model model) {

		RegistrationForm RegistrationForm = new RegistrationForm();
		
		model.addAttribute("registrationForm", RegistrationForm);
		
		return "register";
	}
	
	@PostMapping
	public String processRegistration(Model model, 
			@ModelAttribute("registrationForm") 
			@Validated
			RegistrationForm registrationForm,		
			BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "register";
		}
		try {
			registrationForm.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
			accountDAO.save(registrationForm);
		} catch (Exception e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			String message = rootCause.getMessage();
			model.addAttribute("errorMessage", message);
			return "register";
		}

		return "redirect:/account/login";
	}
}
