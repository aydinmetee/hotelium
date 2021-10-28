package com.hotelium.mainservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Mete Aydin
 * @date 28.10.2021
 */
@Configuration
public class I18NConfig {
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource bundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/messages");
        messageSource.setDefaultEncoding("ISO8859_9");
        return messageSource;
    }
}
