package com.example.FORMANTO.config;

import com.example.FORMANTO.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@RequiredArgsConstructor
public class
WebSecurityConfig {

    private final UserDetailService userDetailService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){ //현재 테스트 단계로 모든 요청에 대해 예외로 한다
        return web -> web.ignoring()
                .requestMatchers("/img/**", "/js/**", "/css/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //filter chain
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/signup", "/user", "/main", "/productDetail/**", "/order", "/api/payment", "/api/QnAQuestion" ,"/authMail").permitAll() //해당 요청에 대해 인증 인가 x
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated() //나머지 요청에 대해 인증
        ).formLogin(in -> in //로그인 설정
                .loginPage("/login")
                .defaultSuccessUrl("/main", true)
        ).logout(out -> out //로그아웃 설정
                .logoutSuccessUrl("/main")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        ).csrf(csrf -> csrf //csrf 설정 테스트 단계로 사용하지 않음
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .disable()
        ).build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    } //암호화 bean

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){ //provider bean
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }
}
