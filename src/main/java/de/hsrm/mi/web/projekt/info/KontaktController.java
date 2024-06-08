package de.hsrm.mi.web.projekt.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class KontaktController {

    Logger logger = LoggerFactory.getLogger(KontaktController.class);

    @GetMapping("/kontakt")
    public String getKontakt(Model m) {
        
        // logger.info("bnummer = {}", bnummer);
        
        return "kontakt";
    }

}
