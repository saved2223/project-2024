package ds.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize
                            .antMatchers("/admin/**").hasRole("ADMIN")
                            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                            .antMatchers("/public/**").permitAll()
                            .antMatchers("/anyUser/**").hasAnyRole("ADMIN", "USER")
                            .anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
