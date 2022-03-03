package com.udj.course.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.udj.course.domain.CardPayment;
import com.udj.course.domain.PaymentTicket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

//DEFAULT, jackson requires it.

@Configuration
public class JacksonConfig {
    // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-of- interfaceclass-without-hinting-the-pare

    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            @Override
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CardPayment.class);
                objectMapper.registerSubtypes(PaymentTicket.class);
                super.configure(objectMapper);
            }
        };
    }
}
