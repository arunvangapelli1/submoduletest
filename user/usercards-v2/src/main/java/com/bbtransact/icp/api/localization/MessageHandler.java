package com.bbtransact.icp.api.localization;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    @Autowired
    private MessageSource messageSource;

    public String localizeErrorMessage(String errorCode, Object args[]) {
        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage(errorCode, args, locale);
        return errorMessage;

    }

    public String localizeErrorMessage(String errorCode) {
        return localizeErrorMessage(errorCode, null);
    }

}
