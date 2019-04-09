package com.hancai.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hancai.demo.common.converter.DateJacksonConverter;
import com.hancai.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * 注册Spring Bean
 *
 * @author diaohancai
 */
@Configuration
public class DhcSecurityAutoConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public DateJacksonConverter dateJacksonConverter() {
        return new DateJacksonConverter();
    }

    @Autowired
    @Bean
    public Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean(DateJacksonConverter dateJacksonConverter) {
        Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
        jackson2ObjectMapperFactoryBean.setDeserializers(dateJacksonConverter);
        return jackson2ObjectMapperFactoryBean;
    }

    @Autowired
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return mappingJackson2HttpMessageConverter;
    }

}
