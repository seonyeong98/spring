package com.example.springtest.config.auth;

import com.example.springtest.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()//h2-console 화면을 사용하기 위해 해당 옵션들을 disable함
                .and()
                .authorizeRequests() //URL별 권한관리를 설정하는 옵션의 시작점 이게 선언되어야 antMatchers 옵션 사용 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //권한 관리대상을 지정하는 옵션
                .anyRequest().authenticated()
                .and()
                .logout()//logout에 관한 여러 설정의 진입점
                .logoutSuccessUrl("/") //logout성공시 /주소로 이동
                .and()
                .oauth2Login() //Oauth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() //로그인 성공 이후 사용자 정보를 가져올 대 설정들 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 userService 인터페이스의 구현체 등록

    }
}
