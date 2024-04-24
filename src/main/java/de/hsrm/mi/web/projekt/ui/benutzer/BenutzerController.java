package de.hsrm.mi.web.projekt.ui.benutzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BenutzerController {
    
    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    @GetMapping("/benutzer/{bnummer}")
    public String getBenutzer(@PathVariable("bnummer") long bnummer) {
        
        logger.info("bnummer = {}", bnummer);
        
        // m.addAttribute("bnummer", bnummer);
        return "benutzerbearbeiten";
    }
    

}
