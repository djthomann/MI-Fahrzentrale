package de.hsrm.mi.web.projekt.ui.benutzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@SessionAttributes(names = {"formular", "maxwunsch"})
public class BenutzerController {
    
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
        
        logger.info("bnummer = {}", bnummer);
        
        m.addAttribute("bnummer", bnummer);
        return "benutzerbearbeiten";
    }

    @PostMapping("/benutzer/{bnummer}")
    public String postMethodName(
    @RequestParam("name") String name,
    @RequestParam("mail") String mail,
    @RequestParam("like") String like,
    @RequestParam("dislike") String dislike,
    @PathVariable("bnummer") long bnummer, 
    @ModelAttribute("formular") BenutzerFormular formular, 
    Model m) {
        
        formular.setMail(mail);
        formular.setName(name);

        if(like != "" && formular.likeAmount() < MAXWUNSCH) {
            formular.addLike(like);
            logger.info("like added: {}", like);
            like = "";
        }

        if(dislike != "" && formular.dislikeAmount() < MAXWUNSCH) {
            formular.addDislike(dislike);
            logger.info("dislike added: {}", dislike);
            dislike = "";
        }

        logger.info("mail = {}", formular.getMail());
        logger.info("name = {}", formular.getName());
        
        return "benutzerbearbeiten";
    }
    
    

}
