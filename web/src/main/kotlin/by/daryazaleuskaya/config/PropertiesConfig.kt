package by.daryazaleuskaya.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource

@Configuration
open class PropertiesConfig {

    @Bean
    open fun messageSource() : MessageSource {

        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:errorMessages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}