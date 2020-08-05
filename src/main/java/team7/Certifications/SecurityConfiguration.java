package team7.Certifications;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("admin").authorities("ADMIN_ACCESS")
       .and().withUser("user").password(passwordEncoder().encode("user")).roles("user").authorities("USER_ACCESS");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.csrf().disable().authorizeRequests()
                .antMatchers("/api/requests/admin/**").hasAuthority("ADMIN_ACCESS")
                .antMatchers("/api/certifications/admin/**").hasAuthority("ADMIN_ACCESS")
                .antMatchers("/api/users/admin/addUser").hasAuthority("ADMIN_ACCESS")
                .antMatchers("/api/requests/user/**").hasAuthority("USER_ACCESS")
                .antMatchers("/api/certifications/allCertifications").hasAnyAuthority("ADMIN_ACCESS","USER_ACCESS")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }
}
