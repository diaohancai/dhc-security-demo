package com.hancai.demo.config;

import com.hancai.browser.authentication.filter.KaptchaFilter;
import com.hancai.browser.authentication.handle.DhcAuthenticationFailureHandler;
import com.hancai.browser.authentication.handle.DhcAuthenticationSuccessHandler;
import com.hancai.core.config.DhcSecurityCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 安全配置
 *
 * @author diaohancai
 */
@Configuration
public class DhcWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private DhcSecurityCoreProperties dhcSecurityCoreProperties;

    private DhcAuthenticationSuccessHandler dhcAuthenticationSuccessHandler;

    private DhcAuthenticationFailureHandler dhcAuthenticationFailureHandler;

    private KaptchaFilter kaptchaFilter;

    @Autowired
    public DhcWebSecurityConfigurer(DhcSecurityCoreProperties dhcSecurityCoreProperties,
                                    DhcAuthenticationSuccessHandler dhcAuthenticationSuccessHandler,
                                    DhcAuthenticationFailureHandler dhcAuthenticationFailureHandler,
                                    KaptchaFilter kaptchaFilter) {
        this.dhcSecurityCoreProperties = dhcSecurityCoreProperties;
        this.dhcAuthenticationSuccessHandler = dhcAuthenticationSuccessHandler;
        this.dhcAuthenticationFailureHandler = dhcAuthenticationFailureHandler;
        this.kaptchaFilter = kaptchaFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(kaptchaFilter, UsernamePasswordAuthenticationFilter.class) // 验证码拦截器
                .formLogin() // 表单登录
                .loginPage("/api/browser-security/authentication-require") // 指定登录页面
                .loginProcessingUrl(dhcSecurityCoreProperties.getBrowser().getLoginProcessUrl()) // 指定登录处理url
                //.successHandler(dhcAuthenticationSuccessHandler) // 认证成功处理器
                //.failureHandler(dhcAuthenticationFailureHandler) // 认证失败处理器
                .and()
                .authorizeRequests()
                .antMatchers(dhcSecurityCoreProperties.getBrowser().getLoginPage(),
                        "/",
                        "/static/**",
                        "/api/browser-security/authentication-require",
                        "/api/kaptcha").permitAll() // 指定的资源，免登录
                .anyRequest()
                .authenticated() // 所有请求均要认证
                .and()
                .csrf().disable() // 临时关闭csrf安全校验
        ;
    }

}
