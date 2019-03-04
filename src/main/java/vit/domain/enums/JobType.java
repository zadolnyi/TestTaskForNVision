package vit.domain.enums;

/**
 * Created by zadolnyi on 17.01.2019.
 */
public enum JobType {PRINT, COPY, SCAN, FAX;

    public static Integer getOrdinal(String value) {
        return valueOf(value.toUpperCase()).ordinal();
    }
}
