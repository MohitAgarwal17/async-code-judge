package com.mohit.asyn_code_judge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class LanguagePolicy {
    private final List<String> supportedLanguages;

    public LanguagePolicy(@Value("${judge.supported-languages}") List<String> supportedLanguages) {
        this.supportedLanguages=supportedLanguages;
    }

    public boolean isSupported(String language){
        return supportedLanguages.contains(language.toLowerCase(Locale.ROOT));
    }
}
