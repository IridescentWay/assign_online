package com.vere.assign_online.config;

import com.vere.assign_online.common.filter.JwtAuthenticationFilter;
import com.vere.assign_online.common.filter.RestAuthenticationEntryPoint;
import com.vere.assign_online.common.filter.VerificationCodeFilter;
import com.vere.assign_online.common.handler.RestAccessDeniedHandler;
import com.vere.assign_online.model.Account;
import com.vere.assign_online.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * ClassName:SecurityConfiguration
 * Package:com.vere.demo.config
 * Description:
 *
 * @Date:2022/4/17 16:32
 * @Author:3046990030@qq.com
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return  username -> {
            Account user = accountService.getAccountByUsername(username);
            return user;
        };
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public VerificationCodeFilter verificationCodeFilter() {
        return new VerificationCodeFilter();
    }

    /*@Bean
    public CachingContentFilter cachingContentFilter() { return new CachingContentFilter(); }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/student/**").hasAnyRole("student", "admin")
                .antMatchers("/teacher/**").hasAnyRole("teacher", "admin")
                .anyRequest().authenticated()
                .and()
                .headers()
                .cacheControl();
		// http.addFilterBefore(cachingContentFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(verificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        
        http.exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /**
     * 配置放行路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/student/login",
                "/teacher/login",
                "/static/public/**"
        );
    }
}
