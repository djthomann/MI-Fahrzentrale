package de.hsrm.mi.web.projekt.services.benutzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;
import de.hsrm.mi.web.projekt.info.ImpressumController;

@Service
public class BenutzerUserDetailsService implements UserDetailsService {

    @Autowired
    BenutzerRepository repository;

    Logger logger = LoggerFactory.getLogger(BenutzerUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Retrieving user data for username: " + username);

        Benutzer user = repository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        logger.info("Found user: " + user.toString());

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .roles(user.getLikes().contains("MACHT") ? "CHEF" : "USER")
                .build();
    }

}
