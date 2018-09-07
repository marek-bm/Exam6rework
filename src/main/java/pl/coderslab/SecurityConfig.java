package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
//                 .withUser("admin")
//                  .password(passwordEncoder()
//                        .encode("admin"))
//                        .roles("ADMIN", "USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/tag/*").authenticated()
                .antMatchers("/moderator/**").hasRole("MODERATOR")
                .antMatchers("/panel/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
//                .anyRequest().permitAll()
                .and().formLogin().successForwardUrl("/panel")
                .and().logout().logoutSuccessUrl("/logoutpage");
    }


}
