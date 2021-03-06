package com.dayeon.insdagram;

import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService accountService;
    @Autowired
    private AuthenticationFailureHandler customFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler customSuccessHandler;
    @Autowired
    private AccountRepository accountRepository;



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // 페이지 권한 설정
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/newposts", "/user/edit").hasRole("MEMBER")
//                .antMatchers("/", "user/**", "/accounts/emailsignup").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/")
//                .defaultSuccessUrl("/newposts")
                .successHandler(customSuccessHandler)
                .failureHandler(customFailureHandler)
                .permitAll()
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
            .and() // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied");
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }



}
