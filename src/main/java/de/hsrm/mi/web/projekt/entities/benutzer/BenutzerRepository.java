package de.hsrm.mi.web.projekt.entities.benutzer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
    Optional<Benutzer> findByMail(String mail);
}
