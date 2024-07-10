package de.hsrm.mi.web.projekt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder() { // @Bean -> Encoder woanders per @Autowired abrufbar
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserBuilder userbuilder = User.withDefaultPasswordEncoder(); // Klartext-Passwort codiert speichern
        UserDetails user1 = userbuilder.username("joendhard@diebiffels.de").password("17")
                .roles("CHEF").build();
        return new InMemoryUserDetailsManager(user1);
    }

}
