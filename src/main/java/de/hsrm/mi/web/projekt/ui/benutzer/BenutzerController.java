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
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerServiceImpl;
import jakarta.validation.Valid;



@Controller
@SessionAttributes(names = {"benutzer", "formular", "maxwunsch"})
public class BenutzerController {
    
    @Autowired BenutzerServiceImpl benutzerService;

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

    @GetMapping("/benutzer/{bnummer}")
    public String getBenutzer(@PathVariable("bnummer") long bnummer, Model m) {
        
        logger.info("Get Benutzer, bnummer = {}", bnummer);
        
        m.addAttribute("bnummer", bnummer);

        if(bnummer == 0) {
            // Neuen Benutzer anlegen
        } else if(bnummer > 0) {
            // Bestehenden Benutzer bearbeiten

            // Muss überprüft werden!
            Optional<Benutzer> benutzer = benutzerService.holeBenutzerMitId(bnummer);

            m.addAttribute("benutzer", benutzer);
            // m.addAttribute("formular", )
        }

        return "benutzerbearbeiten";
    }

    @PostMapping("/benutzer/{bnummer}")
    public String postMethodName(
        @PathVariable("bnummer") long bnummer,
        @Valid @ModelAttribute("formular") BenutzerFormular formular,
        BindingResult formularFehler,
        Model m) {
        
        // formular.setMail();
        // formular.setName("name");
        // formular.setBirthday("birthday");

        // logger.info("like is: {}", "like");
        // logger.info("dislike is: {}", "dislike");

        String like = formular.getLike();
        if(like != null && like != "" && formular.likeAmount() < MAXWUNSCH) {
            formular.addLike(like);
            // logger.info("like added: {}", like);
            formular.setLike("");
        }

        String dislike = formular.getDislike();
        if(dislike != null && dislike != "" && formular.dislikeAmount() < MAXWUNSCH) {
            formular.addDislike(dislike);
            // logger.info("dislike added: {}", dislike);
            formular.setDislike("");
        }

        // logger.info("mail = {}", formular.getMail());
        // logger.info("name = {}", formular.getName());
        // logger.info("birthday = {}", birthday);
        // logger.info("birthday = {}", formular.getBirthday());

        m.addAttribute("bnummer", bnummer);

        return "benutzerbearbeiten";
    }
    
    

}
