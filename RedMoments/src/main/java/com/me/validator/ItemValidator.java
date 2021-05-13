package com.me.validator;

import com.me.dao.ItemDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class ItemValidator implements Validator {

	@Autowired
	@Qualifier("itemDao")
	ItemDAO itemDao;

	public boolean supports(Class aClass) {
		return aClass.equals(Item.class);
	}

	private Pattern pattern;
	private Matcher matcher;

	private static final String PRICE_PATTERN = "[0-9]+";
	private static final String STRING_PATTERN = "[a-zA-Z]+";

	public void validate(Object obj, Errors errors) {
		Item newItem = (Item) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Please enter a Title");
		if (!(newItem.getTitle() != null && newItem.getTitle().isEmpty())) {
			String title = stripXSS1(newItem.getTitle());
			newItem.setTitle(title);
		}


		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "error.invalid.message", "Please enter a Description");
		if (!(newItem.getMessage() != null && newItem.getMessage().isEmpty())) {
			String title = stripXSS1(newItem.getMessage());
			newItem.setMessage(title);
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.price", "Please enter a Cost");
		if (!(newItem.getPrice() != null && newItem.getPrice() != 0 && !(newItem.getPrice()<0))) {
			pattern = Pattern.compile(PRICE_PATTERN);
			matcher = pattern.matcher(String.valueOf(newItem.getPrice()));
			if (!matcher.matches()) {
				errors.rejectValue("price", "price.containNonChar",
					"Enter a valid Cost");
			}
		}

	}

	private String stripXSS1(String value) {
		if (value != null) {
		
			value = value.replaceAll("", "");

			Pattern scriptPattern = Pattern.compile("insert", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("delete", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("update", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("drop", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("--", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}

}