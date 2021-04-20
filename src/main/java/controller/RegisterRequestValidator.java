package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterRequestValidator implements Validator {
//	private static final String emailRegExp = 
//			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
//			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//	private Pattern pattern;
//
//	public RegisterRequestValidator() {
//		pattern = Pattern.compile(emailRegExp);
//		System.out.println("RegisterRequestValidator#new(): " + this);
//	}
//
	private static final String pwRegExp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}";
	
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile(pwRegExp);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("RegisterRequestValidator#validate(): " + this);
		RegisterCommand regReq = (RegisterCommand) target;
		if (regReq.getId() == null || regReq.getId().trim().isEmpty()) {
			errors.rejectValue("id", "required");
		} else {
			Matcher matcher = pattern.matcher(regReq.getPw());
			if (!matcher.matches()) {
				errors.rejectValue("pw", "bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmpty(errors, "pw", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPw", "required");
		if (!regReq.getPw().isEmpty()) {
			if (!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPw", "nomatch");
			}
		}
	}

}
