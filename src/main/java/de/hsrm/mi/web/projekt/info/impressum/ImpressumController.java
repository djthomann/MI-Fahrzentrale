package de.hsrm.mi.web.projekt.info.impressum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ImpressumController {

    Logger logger = LoggerFactory.getLogger(ImpressumController.class);

    @GetMapping("/impressum")
    public String getImpressum(Model m) {
        
        // logger.info("bnummer = {}", bnummer);
        
        return "impressum";
    }

}
