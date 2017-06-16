package fi.soveltia.arquillian.util;

import javax.portlet.ActionRequest;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Validator;

public class EntryValidator {

	public static final int AUTHOR_MAX_LENGTH = 10;
	public static final int TEXT_MAX_LENGTH = 100;
	
	public static void validate(ActionRequest request, String author, String text) {
		
		if (Validator.isNull(author)) {
			SessionErrors.add(request, "error-author-cannot-be-empty");
		}

		if (author.length() > AUTHOR_MAX_LENGTH) {
			SessionErrors.add(request, "error-author-too-long");
		}
		
		if (Validator.isNull(text)) {
			SessionErrors.add(request, "error-text-cannot-be-empty");
		}

		if (author.length() > TEXT_MAX_LENGTH) {
			SessionErrors.add(request, "error-text-too-long");
		}
	}
}
