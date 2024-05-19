package de.hsrm.mi.web.projekt.ui.benutzer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerService;
import jakarta.validation.Valid;



@Controller
@SessionAttributes(names = {"benutzer", "formular", "info","maxwunsch"})
public class BenutzerController {
    
    @Autowired BenutzerService benutzerService;

    public final int MAXWUNSCH = 5;

    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    @ModelAttribute("formular")
    public void initForm(Model m) {

        // logger.info("formular init");

        m.addAttribute("formular", new BenutzerFormular());
    }

    @ModelAttribute("maxwunsch")
    public void initMaxwunsch(Model m) {
        m.addAttribute("maxwunsch", MAXWUNSCH);
    }

    @GetMapping("/benutzer")
    public String getBenutzerListe(Model m) {
        return "benutzerliste";
    }
    

    @GetMapping("/benutzer/{bnummer}")
    public String getBenutzerBearbeiten(@PathVariable("bnummer") long bnummer, Model m) {
        
        logger.info("GetMapping: /benutzer/bnummer = {}", bnummer);
        
        m.addAttribute("bnummer", bnummer);
        m.addAttribute("info", null);

        if(bnummer == 0) {
            // Neuen Benutzer anlegen
            logger.info("Neuen Benutzer anlegen");

            m.addAttribute("benutzerformular", new BenutzerFormular());
            m.addAttribute("benutzer", new Benutzer());

            logger.info(m.getAttribute("benutzerformular").toString());


        } else if(bnummer > 0) {
            // Bestehenden Benutzer bearbeiten
            logger.info("Get: Bestehenden Benutzer bearbeiten");

            Optional<Benutzer> optionalBenutzer = benutzerService.holeBenutzerMitId(bnummer);
            if(optionalBenutzer.isEmpty()) {
                logger.info("Benutzer konnte nicht gefunden werden");
                m.addAttribute("info", "Benutzer konnte nicht gefunden werden");
                return "kontakt";
            } else {
                Benutzer benutzer = optionalBenutzer.get();
                BenutzerFormular benutzerFormular = new BenutzerFormular();
                benutzerFormular.fromBenutzer(benutzer);

                m.addAttribute("benutzer", benutzer);
                m.addAttribute("formular", benutzerFormular);
            }
            
        } else {
            // bnummer < 0 --> Was tun?
        }

        return "benutzerbearbeiten";
    }

    @PostMapping("/benutzer/{bnummer}")
    public String postBenutzerBearbeiten(
        @PathVariable("bnummer") long bnummer,
        @SessionAttribute("benutzer") Benutzer benutzer,
        @Valid @ModelAttribute("formular") BenutzerFormular formular,
        BindingResult formularFehler,
        Model m) {

            logger.info("PostMapping: /benutzer/bnummer = {}", bnummer);

        if(formularFehler.hasErrors()) {
            // Zurück ins Formular schicken

        } else {
            // An die Datenbank schicken
            logger.info("Controller versucht speichern zu lassen");
            logger.info("Passwort = {}", formular.getPassword());

            // Benutzer-Entity setzen
            formular.toBenutzer(benutzer);
            if(formular.getPassword() != null) {
                benutzer.setPassword(formular.getPassword());
            }

            try {
                benutzer = benutzerService.speichereBenutzer(benutzer);
                bnummer = benutzer.getId();
                logger.info("bnummer", bnummer);
                return "impressum";
            } catch(Exception e) {
                logger.error("Fehler beim Speichern", e);
                m.addAttribute("info", e.getMessage());
                return "benutzerbearbeiten";
            }
        }

        String like = formular.getLike();
        if(like != null && like != "" && formular.likeAmount() < MAXWUNSCH) {
            formular.addLike(like);
            logger.info("like added: {}", like);
            formular.setLike("");
        }

        String dislike = formular.getDislike();
        if(dislike != null && dislike != "" && formular.dislikeAmount() < MAXWUNSCH) {
            formular.addDislike(dislike);
            logger.info("dislike added: {}", dislike);
            formular.setDislike("");
        }

        return "benutzerbearbeiten";
    }
    
    

}
