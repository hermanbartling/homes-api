package se.difo.hemnetapi.util;

import se.difo.dates.TimeStampUtils;

import java.time.Instant;

public class Temporal extends TimeStampUtils {

    public static String asDbString(Instant instant) {
        return toDbDateTimeFormat(asString(instant));
    }

    public static Instant fromDbString(String dbString) {
        return asInstant(toDomainDateTimeFormat(dbString));
    }

    public static String toDbDateTimeFormat(String domainFormat) {
        if (domainFormat != null) {
            return domainFormat.replaceAll("T", " ").replaceAll("Z", "");
        }
        return null;
    }

    public static String toDomainDateTimeFormat(String dbFormat) {
        if (dbFormat != null) {
            StringBuilder myName = new StringBuilder(dbFormat);
            myName.setCharAt(10, 'T');

            if (myName.length() < 21) {
                myName.append('Z');
            } else {
                myName.setCharAt(19, 'Z');
            }

            return myName.substring(0, 20);
        }
        return null;
    }

}
