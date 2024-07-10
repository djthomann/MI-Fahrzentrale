package de.hsrm.mi.web.projekt.ui.benutzer;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/benutzer")
@SessionAttributes(names = { "benutzer", "formular", "maxwunsch" })
public class BenutzerController {

    @Autowired
    BenutzerService benutzerService;

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

    @GetMapping("")
    public String getBenutzerListe(Model m) {

        m.addAttribute("info", null);
        List<Benutzer> benutzer = benutzerService.holeAlleBenutzer();
        logger.info("Get Benutzer, Länge: " + benutzer.size());
        m.addAttribute("benutzer", benutzer);

        return "benutzer/benutzerliste";
    }

    @GetMapping("/{bnummer}/del")
    public String deleteBenutzer(@PathVariable("bnummer") long bnummer, Model m) {

        benutzerService.loescheBenutzerMitId(bnummer);

        return "redirect:/benutzer";
    }

    @GetMapping("/{bnummer}")
    public String getBenutzerBearbeiten(@PathVariable("bnummer") long bnummer, Model m) {

        logger.info("GetMapping: /benutzer/bnummer = {}", bnummer);

        m.addAttribute("bnummer", bnummer);
        m.addAttribute("info", null);

        if (bnummer == 0) {
            // Neuen Benutzer anlegen
            logger.info("Neuen Benutzer anlegen");

            m.addAttribute("formular", new BenutzerFormular());
            m.addAttribute("benutzer", new Benutzer());

            logger.info(m.getAttribute("formular").toString());
            return "benutzer/benutzerbearbeiten";

        } else if (bnummer > 0) {
            // Bestehenden Benutzer bearbeiten
            logger.info("Get: Bestehenden Benutzer bearbeiten");

            Optional<Benutzer> optionalBenutzer = benutzerService.holeBenutzerMitId(bnummer);
            if (optionalBenutzer.isEmpty()) {
                logger.info("Benutzer konnte nicht gefunden werden");
                m.addAttribute("info", "Benutzer konnte nicht gefunden werden");
                return "benutzer/benutzerliste";
            } else {
                Benutzer benutzer = optionalBenutzer.get();
                BenutzerFormular benutzerFormular = new BenutzerFormular();
                benutzerFormular.fromBenutzer(benutzer);

                m.addAttribute("benutzer", benutzer);
                m.addAttribute("formular", benutzerFormular);
                return "benutzer/benutzerbearbeiten";
            }

        } else {
            m.addAttribute("info", "Invalide Benutzer ID");
            return "benutzer/benutzerbearbeiten";
        }

    }

    @PostMapping("/{bnummer}")
    public String postBenutzerBearbeiten(
            @PathVariable("bnummer") long bnummer,
            @SessionAttribute("benutzer") Benutzer benutzer,
            @Valid @ModelAttribute("formular") BenutzerFormular formular,
            BindingResult formularFehler,
            Model m) {

        logger.info("PostMapping: /benutzer/bnummer = {}", bnummer);

        String like = formular.getLike();
        if (like != null && like != "" && formular.likeAmount() < MAXWUNSCH) {
            formular.addLike(like);
            logger.info("like added: {}", like);
            formular.setLike("");
        }

        String dislike = formular.getDislike();
        if (dislike != null && dislike != "" && formular.dislikeAmount() < MAXWUNSCH) {
            formular.addDislike(dislike);
            logger.info("dislike added: {}", dislike);
            formular.setDislike("");
        }

        if (formularFehler.hasErrors()) {
            // Zurück ins Formular schicken
            return "benutzer/benutzerbearbeiten";
        } else {
            // An die Datenbank schicken

            logger.info("Controller versucht speichern zu lassen");
            logger.info("Passwort = {}", formular.getPassword());

            // Benutzer-Entity setzen
            formular.toBenutzer(benutzer);
            if (formular.getPassword() != "") {
                benutzer.setPassword(formular.getPassword());
            }

            try {
                Benutzer benutzerNeu = benutzerService.speichereBenutzer(benutzer);
                long bnummerNeu = benutzerNeu.getId();
                logger.info("bnummer", bnummerNeu);
                return "redirect:/benutzer/" + bnummerNeu;
            } catch (Exception e) {
                logger.error("Fehler beim Speichern", e);
                m.addAttribute("info", e.getMessage());
                return "benutzer/benutzerbearbeiten";
            }
        }

    }

    @GetMapping("/{id}/hx/feld/{feldname}")
    public String getMethodName(@PathVariable("id") long id, @PathVariable("feldname") String feldname, Model m) {

        Optional<Benutzer> optionalBenutzer = benutzerService.holeBenutzerMitId(id);
        Benutzer b = optionalBenutzer.get();

        if (feldname.equals("email")) {
            m.addAttribute("wert", b.getMail());
        } else if (feldname.equals("name")) {
            m.addAttribute("wert", b.getName());
        } else if (feldname.equals("surname")) {
            m.addAttribute("wert", b.getSurname());
        }

        m.addAttribute("bnummer", id);

        return "benutzer/benutzerliste-zeile :: feldbearbeiten";
    }

    @PutMapping("/{id}/hx/feld/{feldname}")
    public String putMethodName(@RequestParam("wert") String wert, @PathVariable("id") long id,
            @PathVariable("feldname") String feldname, Model m) {

        m.addAttribute("bnummer", id);
        try {
            benutzerService.aktualisiereBenutzerAttribut(id, feldname, wert);
            m.addAttribute("wert", wert);
            return "benutzer/benutzerliste-zeile :: feldausgeben";
        } catch (Exception e) {

            Optional<Benutzer> optionalBenutzer = benutzerService.holeBenutzerMitId(id);
            Benutzer b = optionalBenutzer.get();

            if (feldname.equals("email")) {
                m.addAttribute("wert", b.getMail());
            } else if (feldname.equals("name")) {
                m.addAttribute("wert", b.getName());
            } else if (feldname.equals("surname")) {
                m.addAttribute("wert", b.getSurname());
            }

            return "benutzer/benutzerliste-zeile :: feldbearbeiten";
        }
    }

}
