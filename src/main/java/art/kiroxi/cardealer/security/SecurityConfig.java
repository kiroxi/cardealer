package art.kiroxi.cardealer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/error").permitAll()

                .antMatchers("/cars/add", "/cars/edit/**").hasRole("ADMIN")
                .antMatchers("/services/add", "/services/edit/**", "/services/delete/**").hasRole("ADMIN")
                .antMatchers("/employees/add", "/employees/edit/**", "/employees/delete/**").hasRole("ADMIN")

                .antMatchers("/**").hasAnyRole("ADMIN", "MANAGER")

                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                    .csrf()
                    .disable();

        return security.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin =
                User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails manager =
                User.builder()
                .username("manager")
                .password(bCryptPasswordEncoder().encode("manager"))
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(admin, manager);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .antMatchers("/css/**", "/fonts/**", "/icons/**", "/img/**", "/js/**");
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
