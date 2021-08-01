package com.java.mysql.Crud.CrudFileOperations;

import org.apache.commons.validator.routines.EmailValidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class Validator {
	
	private EmailValidator validator;
	private PhoneNumberUtil phoneUtil;
	//private Connection conn;
	
	
	public Validator() {
		
		 validator = EmailValidator.getInstance();
		 phoneUtil = PhoneNumberUtil.getInstance();
		
	}

	public boolean emailValidator(String email)
	{
		// Get an `EmailValidator` instance
		//EmailValidator validator = EmailValidator.getInstance();

		// Validate specified string containing an email address
		if (!validator.isValid(email)) {
			return false;
		}
		return true;
	}

	public boolean isPhoneNumberValid(String phone)
	{
		// creating an instance of PhoneNumber Utility class
		//PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

		// creating a variable of type PhoneNumber
		PhoneNumber phoneNumber = null;

		// this statement prints the type of the phone
		// number
		try {
			phoneNumber = phoneUtil.parse(phone, "IN");
			System.out.println("\nType: "+ phoneUtil.getNumberType(phoneNumber));
		} catch (NumberParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to parse the given phone number: "+ phone);
			e.printStackTrace();
		}
		// return the boolean value of the validation
		return phoneUtil.isValidNumber(phoneNumber);
	}
	
}
