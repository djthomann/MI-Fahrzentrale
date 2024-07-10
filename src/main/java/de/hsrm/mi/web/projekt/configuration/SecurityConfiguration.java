package de.hsrm.mi.web.projekt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        PasswordEncoder passwordEncoder() { // @Bean -> Encoder woanders per @Autowired abrufbar
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

        // @Bean
        // public UserDetailsService userDetailsService() {
        // UserBuilder userbuilder = User.withDefaultPasswordEncoder(); //
        // Klartext-Passwort codiert speichern

        // UserDetails user1 =
        // userbuilder.username("joendhard@diebiffels.de").password("17")
        // .roles("CHEF").build();

        // UserDetails user2 =
        // userbuilder.username("detlef@dingenskirchen.de").password("1234")
        // .roles("USER").build();

        // return new InMemoryUserDetailsManager(user1, user2);
        // }

        @Bean
        SecurityFilterChain filterChainApp(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(toH2Console()).permitAll()
                                .requestMatchers("/admin/ort").hasRole(
                                                "CHEF")
                                .requestMatchers("/admin/ort/*").hasRole(
                                                "CHEF")
                                .requestMatchers("/admin/*").authenticated()
                                .anyRequest().permitAll())

                                .formLogin(form -> form
                                                .defaultSuccessUrl("/admin/tour", true)
                                                .permitAll())
                                .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
                                .csrf(csrf -> csrf.ignoringRequestMatchers("/admin/benutzer/*/hx/feld/*"))
                                .headers(hdrs -> hdrs.frameOptions(fo -> fo.sameOrigin()))
                                .logout(out -> out.logoutSuccessUrl("/login"));

                return http.build();
        }

}
