package pl.com.navcity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;
import pl.com.navcity.service.UserDetailsServiceImpl;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;

    @Autowired
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
                .antMatchers("/","/api").hasAnyRole("DRIVER", "ADMIN", "MANAGER")
                .antMatchers("/api/routes/**").permitAll()
                .antMatchers("/api/cars/**").permitAll()
                .antMatchers("/api/reports/**").hasRole("MANAGER")
                .and()
                .formLogin()
                    .loginPage("/login-form")
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/api/routes/list")
                    .permitAll()
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login-form?logout")
                    .permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
