package orion.validation.validator;

import orion.controller.Notification;
import orion.core.Utility;
import orion.validation.field.RequiredStringField;

public class RequiredStringValidator {

	public static boolean validate(Notification notification, RequiredStringField... fields) {
		for (RequiredStringField field : fields) {

			if (field.getMessage() == null) {
				field.setMessage("Field " + field.getName() + " is required");
			}

			if (!(field.getValue() instanceof String)) {
				if (field.isRequired()) {
					notification.addFieldError(field.getName(), field.getMessage());
					return false;
				}
				continue;
			}

			String value = (String) field.getValue();

			if (field.isStrip()) {
				value = Utility.stripText(value);
			}

			if (value.isEmpty()) {
				notification.addFieldError(field.getName(), field.getMessage());
				return false;
			}

		}
		return true;
	}

}
