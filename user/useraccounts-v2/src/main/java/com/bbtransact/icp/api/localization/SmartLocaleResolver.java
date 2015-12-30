package com.bbtransact.icp.api.localization;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;

public class SmartLocaleResolver extends CookieLocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String acceptLanguage = request.getHeader("Accept-Language");
        if (acceptLanguage == null || acceptLanguage.trim().isEmpty()) {
            System.out.println("acceptlanguage is null");
            return super.determineDefaultLocale(request);
        }
        return request.getLocale();
    }

}
