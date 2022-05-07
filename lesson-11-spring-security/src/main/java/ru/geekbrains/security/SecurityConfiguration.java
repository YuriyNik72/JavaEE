package ru.geekbrains.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth,
                           UserDetailsService userDetailsService,
                           PasswordEncoder encoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mem_admin")
                .password(encoder.encode("pass"))
                .roles("SUPERADMIN")
                .and()
                .withUser("mem_guest")
                .password(encoder.encode("pass"))
                .roles("GUEST");

        auth.userDetailsService(userDetailsService);
    }

    @Configuration
    public static class UiSecurityConfigureAdapter extends WebSecurityConfigurerAdapter{

        @Value("${server.servlet.context-path}")
        private String contextPath;

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*js").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/access_denied").authenticated()
                    .antMatchers("/user/**").hasAnyRole("ADMIN", "SUPERADMIN")
                    .and()
                    .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/");
                    .successHandler((req,resp, auth)->{
                        Set<String> auths = auth.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet());
                        if(auths.contains("ROLE_ADMIN") || auths.contains("ROLE_SUPERADMIN")){
                            resp.sendRedirect(contextPath + "/user");
                        }else {
                            resp.sendRedirect(contextPath + "/");
                        }
                    })
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");

        }
    }
}
