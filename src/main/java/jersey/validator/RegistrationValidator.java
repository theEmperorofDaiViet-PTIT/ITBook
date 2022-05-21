package jersey.validator;

import org.apache.commons.validator.routines.EmailValidator;

import jersey.dao.AccountDAO;
import jersey.entity.Account;
import jersey.form.RegistrationForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {
	
	@Autowired
	private AccountDAO accountDAO;
	
	private EmailValidator emailValidator = EmailValidator.getInstance();

	// Validator này chỉ dùng để kiểm tra đối với CustomerForm.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == RegistrationForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegistrationForm regInfo = (RegistrationForm) target;

		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.registrationForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.registrationForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.registrationForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.registrationForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.registrationForm.phone");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.registrationForm.address");		

		if (!emailValidator.isValid(regInfo.getEmail())) {
			errors.rejectValue("email", "Pattern.registrationForm.email");
		}
		String userName = regInfo.getUserName();
		if (userName != null && userName.length() > 0) {
			if (userName.matches("\\s+")) {
				errors.rejectValue("userName", "Pattern.registrationForm.userName");
			} else if (regInfo.isNewAccount()) {
				Account account = accountDAO.findAccount(userName);
				if (account != null) {
					errors.rejectValue("userName", "Duplicate.registrationForm.userName");
				}
			}
		}
	}
}
