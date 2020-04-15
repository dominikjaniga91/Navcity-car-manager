package pl.com.navcity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("Dominik").password("dominik123").roles("ADMIN"))
                .withUser(users.username("Daria").password("daria123").roles("DRIVER"))
                .withUser(users.username("Maciek").password("maciek123").roles("MANAGER"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("DRIVER", "ADMIN", "MANAGER")
                .antMatchers("/routes/**").permitAll()
                .antMatchers("/cars/**").permitAll()
                .antMatchers("/reports/**").hasRole("MANAGER")
                .antMatchers("/drivers/**").hasRole("DRIVER")
                .antMatchers("/showReportsPage").hasRole("DRIVER")
                .and()
                .formLogin().loginPage("/login-form").permitAll()
                .and()
                .logout().permitAll();

    }
}
