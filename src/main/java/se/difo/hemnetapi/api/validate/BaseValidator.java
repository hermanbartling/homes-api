package se.difo.hemnetapi.api.validate;

import se.difo.dates.TimeStampUtils;
import se.difo.hemnetapi.core.exception.ApiValidationException;


public class BaseValidator {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    protected static void validateTimeStamp(String timestamp) {
        try {
            TimeStampUtils.asInstant(timestamp);
        } catch (Exception e) {
            throw new ApiValidationException(
                    "Invalid time stamp " + timestamp + ", must satisfy pattern " + PATTERN
            );
        }
    }

    protected static void assertNotNull(Object target, String targetName) {
        if (target == null) {
            throw new ApiValidationException(
                    "Parameter " + targetName + " is not allowed to be null"
            );

        }
    }

    protected static void assertNotBlank(String target, String targetName) {
        if (target.length() == 0) {
            throw new ApiValidationException(
                    "Parameter " + targetName + " is not allowed to be blank"
            );

        }
    }

}
