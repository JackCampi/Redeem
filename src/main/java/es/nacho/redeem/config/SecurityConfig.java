package es.nacho.redeem.config;

import es.nacho.redeem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/admin**").hasRole("ADMIN")
                .antMatchers("/emp**").hasAnyRole("ADMIN","EMPLOYEE")
                .antMatchers("/login", "reg**").permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.authenticationProvider(authenticationProvider());

    }

}
