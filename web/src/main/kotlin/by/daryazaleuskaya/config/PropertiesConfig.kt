package by.daryazaleuskaya.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:messages/errorMessages.properties")
open class PropertiesConfig