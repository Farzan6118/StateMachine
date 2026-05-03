package com.example.statemachine.service.impl;


import com.example.statemachine.service.LocalizationService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;

    public LocalizationServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String messageKey, Object[] args) {
//        if (LocaleContextHolder.getLocale().getLanguage().equals("en")) {
//            LocaleContextHolder.setLocale(Locale.ENGLISH);
//        }
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }

    @Override
    public String getMessage(String messageKey, Object[] args, Locale locale) {
//        if (LocaleContextHolder.getLocale().getLanguage().equals("en")) {
//            LocaleContextHolder.setLocale(Locale.ENGLISH);
//        }
        return messageSource.getMessage(messageKey, args, locale);
    }

}
