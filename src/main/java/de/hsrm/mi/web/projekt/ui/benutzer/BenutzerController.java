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
@SessionAttributes(names = {"formular"})
public class BenutzerController {
    
    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    @ModelAttribute("formular")
    public void initForm(Model m) {
        m.addAttribute("formular", new BenutzerFormular());
    }

    @GetMapping("/benutzer/{bnummer}")
    public String getBenutzer(@PathVariable("bnummer") long bnummer, Model m) {
        
        logger.info("bnummer = {}", bnummer);
        
        m.addAttribute("bnummer", bnummer);
        m.addAttribute("formular", new BenutzerFormular());
        return "benutzerbearbeiten";
    }

    @PostMapping("/benutzer/{bnummer}")
    public String postMethodName(
    @RequestParam("name") String name,
    @RequestParam("mail") String mail,
    @PathVariable("bnummer") long bnummer, 
    @ModelAttribute("formular") BenutzerFormular formular, 
    Model m) {
        
        formular.setMail(mail);
        formular.setName(name);

        logger.info("mail = {}", mail);
        logger.info("name = {}", name);
        
        return "benutzerbearbeiten";
    }
    
    

}
