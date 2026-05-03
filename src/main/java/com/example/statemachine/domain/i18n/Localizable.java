package com.example.statemachine.domain.i18n;

import java.util.Locale;

public interface Localizable {

    String getEnTitle();

    void setEnTitle(String enTitle);

    String getFaTitle();

    void setFaTitle(String faTitle);

    String getEnDescription();

    void setEnDescription(String enDescription);

    String getFaDescription();

    void setFaDescription(String faDescription);

    default String getTitle(Locale locale) {
        return resolve(locale, getEnTitle(), getFaTitle());
    }

    default void setTitle(Locale locale, String title) {
        if (locale.getLanguage().equals("en")) {
            setEnTitle(title);
        } else {
            setFaTitle(title);
        }
    }

    default void setDescription(Locale locale, String description) {
        if (locale.getLanguage().equals("en")) {
            setEnDescription(description);
        } else {
            setFaDescription(description);
        }
    }

    default String getDescription(Locale locale) {
        return resolve(locale, getEnDescription(), getFaDescription());
    }

    default String resolve(Locale locale, String en, String fa) {
        if (locale == null) return en;
        if (locale.getLanguage().equals("fa") && fa != null) {
            return fa;
        } else {
            return en;
        }
    }

}
