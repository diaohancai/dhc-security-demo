package com.hancai.demo.config;

import com.hancai.browser.authentication.filter.KaptchaFilter;
import com.hancai.browser.authentication.handle.DhcAuthenticationFailureHandler;
import com.hancai.browser.authentication.handle.DhcAuthenticationSuccessHandler;
import com.hancai.core.config.DhcSecurityCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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

    private PersistentTokenRepository persistentTokenRepository;

    private UserDetailsService userDetailsService;

    @Autowired
    public DhcWebSecurityConfigurer(DhcSecurityCoreProperties dhcSecurityCoreProperties,
                                    DhcAuthenticationSuccessHandler dhcAuthenticationSuccessHandler,
                                    DhcAuthenticationFailureHandler dhcAuthenticationFailureHandler,
                                    KaptchaFilter kaptchaFilter,
                                    PersistentTokenRepository persistentTokenRepository,
                                    UserDetailsService userDetailsService) {
        this.dhcSecurityCoreProperties = dhcSecurityCoreProperties;
        this.dhcAuthenticationSuccessHandler = dhcAuthenticationSuccessHandler;
        this.dhcAuthenticationFailureHandler = dhcAuthenticationFailureHandler;
        this.kaptchaFilter = kaptchaFilter;
        this.persistentTokenRepository = persistentTokenRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(kaptchaFilter, UsernamePasswordAuthenticationFilter.class) // 验证码拦截器
                // 表单登录
                .formLogin()
                    .loginPage("/api/browser-security/authentication-require") // 登录页面
                    .loginProcessingUrl(dhcSecurityCoreProperties.getBrowser().getLoginProcessUrl()) // 登录处理url
                    //.successHandler(dhcAuthenticationSuccessHandler) // 认证成功处理器
                    //.failureHandler(dhcAuthenticationFailureHandler) // 认证失败处理器
                    .permitAll() // 登录相关api全部允许
                    .and()
                // 登出
                .logout()
                    .logoutSuccessUrl(dhcSecurityCoreProperties.getBrowser().getLogoutPage()) // 登出成功跳转路径
                    .logoutUrl(dhcSecurityCoreProperties.getBrowser().getLogoutProcessUrl()) // 登出处理url
                    .clearAuthentication(true) // 清除认证状态
                    .deleteCookies("JSESSIONID")
                    .permitAll() // 登出相关api全部允许
                    .and()
                // 记住我
                .rememberMe()
                    .tokenRepository(persistentTokenRepository)
                    .tokenValiditySeconds(60 * 60)
                    .userDetailsService(userDetailsService)
                    .and()
                // session
                .sessionManagement()
                    .maximumSessions(1) // 用户最多仅一处登录
                    .and()
                    .and()
                // 权限
                .authorizeRequests()
                    // 免登录资源
                    .antMatchers(
                            dhcSecurityCoreProperties.getBrowser().getLoginPage(),
                            "/",
                            "/static/**",
                            "/api/browser-security/authentication-require",
                            "/api/kaptcha").permitAll()
                    // 需要权限资源
                    .antMatchers("/api/admin").hasRole("admin")
                    .antMatchers("/api/manager").hasRole("manager")
                    .antMatchers(HttpMethod.DELETE, "/api/admin").hasRole("admin_delete") // url权限精确到 method
                    .antMatchers(HttpMethod.POST, "/api/manager").hasRole("manager_create")
                    .anyRequest()
                    .authenticated() // 所有请求均要认证
                    .and()
                // csrf
                .csrf()
                    .disable() // 临时关闭csrf安全校验
        ;
    }

}
