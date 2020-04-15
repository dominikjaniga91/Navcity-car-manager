package pl.com.navcity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import pl.com.navcity.service.UserDetailsServiceImpl;
import pl.com.navcity.service.UserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;


    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("DRIVER", "ADMIN", "MANAGER")
                .antMatchers("/routes").permitAll()
                .antMatchers("/cars/**").permitAll()
                .antMatchers("/reports/**").hasRole("MANAGER")
                .antMatchers("/drivers/**").hasRole("DRIVER")
                .antMatchers("/showReportsPage").hasRole("DRIVER")
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/login-form")
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/routes", true)
                    .permitAll()
                .and()
                .logout().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
