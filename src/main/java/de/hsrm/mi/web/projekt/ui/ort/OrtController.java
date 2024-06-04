package de.hsrm.mi.web.projekt.ui.ort;

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

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import jakarta.validation.Valid;

@Controller
@SessionAttributes(names = {"ort", "formular", "info"})
public class OrtController {
    
    @Autowired OrtService ortService; 

    Logger logger = LoggerFactory.getLogger(OrtController.class);

    @ModelAttribute("formular")
    public void initForm(Model m) {
        m.addAttribute("formular", new OrtFormular());
    }

    @GetMapping("/ort")
    public String getOrte(Model m) {

        m.addAttribute("info", null);
        List<Ort> orte = ortService.holeAlleOrt();
        logger.info("Get Orte, Länge: " + orte.size());
        m.addAttribute("orte", orte);

        return "ort/ortliste";
    }

    @GetMapping("/ort/{onummer}/del")
    public String deleteOrt(@PathVariable("onummer") long onummer, Model m) {
        
        ortService.loescheOrtMitId(onummer);

        return "redirect:/ort";
    }

    @GetMapping("/ort/{onummer}")
    public String getOrtBearbeiten(@PathVariable("onummer") long onummer, Model m) {
        
        if(onummer == 0) {
            // Neuen Ort anlegen
            logger.info("Neuen Ort anlegen");

            OrtFormular form = new OrtFormular();
            form.computeBoundingBox();

            m.addAttribute("formular", form);
            m.addAttribute("ort", new Ort());

        } else if(onummer > 0) {
            // Bestehenden Ort bearbeiten

            logger.info("Get: Bestehenden Benutzer bearbeiten");

            Optional<Ort> optionalOrt = ortService.holeOrtMitId(onummer);
            if(optionalOrt.isEmpty()) {
                logger.info("Ort konnte nicht gefunden werden");
                m.addAttribute("info", "Ort konnte nicht gefunden werden");
                return "ort/ortliste";
            } else {
                Ort ort = optionalOrt.get();
                OrtFormular ortFormular = new OrtFormular();
                ortFormular.fromOrt(ort);

                m.addAttribute("ort", ort);
                m.addAttribute("formular", ortFormular);
                return "ort/ortbearbeiten";
            }
        }
        
        return "ort/ortbearbeiten";
    }

    @PostMapping("/ort/{onummer}")
    public String postMethodName(
        @PathVariable("onummer") long onummer,
        @SessionAttribute("ort") Ort ort,
        @Valid @ModelAttribute("formular") OrtFormular formular, 
        BindingResult formularFehler,
        Model m) 
        {

            formular.computeBoundingBox();

            if(formular.getGeobreite() == 0 && formular.getGeolaenge() == 0) {
                List<Ort> vorschlaege = ortService.findeOrtsvorschlaegeFuerAdresse(formular.getName());
                Ort vorschlag1 = vorschlaege.get(0);
                formular.setGeobreite(vorschlag1.getGeobreite());
                formular.setGeolaenge(vorschlag1.getGeolaenge());
                return "ort/ortbearbeiten";
            }

        if(formularFehler.hasErrors()) {

            return "ort/ortbearbeiten";
        } else {
            formular.toOrt(ort);

            try {
                Ort ortNeu = ortService.speichereOrt(ort);
                return "ort/ortbearbeiten";
            } catch(Exception e) {
                m.addAttribute("info", e.getMessage());
            }
        }
        
        return "ort/ortliste";
    }
    
    

}
