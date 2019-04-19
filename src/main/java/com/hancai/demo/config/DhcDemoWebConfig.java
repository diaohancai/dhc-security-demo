package com.hancai.demo.config;

import com.hancai.demo.common.filter.TimeFilter;
import com.hancai.demo.common.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 相当于以前的web.xml
 *
 * @author diaohancai
 */
@Configuration
public class DhcDemoWebConfig extends WebMvcConfigurerAdapter {

    private TimeInterceptor timeInterceptor;

    @Autowired
    public DhcDemoWebConfig(TimeInterceptor timeInterceptor) {
        this.timeInterceptor = timeInterceptor;
    }

    /**
     * 注册Filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        // 指定过滤 url pattern
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/api/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);

        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
