package de.hsrm.mi.web.projekt.ui.tour;

import java.util.List;

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

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.services.tour.TourService;
import de.hsrm.mi.web.projekt.ui.ort.OrtFormular;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@SessionAttributes(names = {"tour", "formular", "info"})
public class TourController {
    
    @Autowired TourService tourService; 

    Logger logger = LoggerFactory.getLogger(TourController.class);

    @ModelAttribute("formular")
    public void initForm(Model m) {
        m.addAttribute("formular", new TourFormular());
    }

    @GetMapping("/tour")
    public String getTouren(Model m) {

        m.addAttribute("info", null);
        List<Tour> touren = tourService.holeAlleTouren();
        logger.info("Get Touren, Länge: " + touren.size());
        m.addAttribute("touren", touren);

        return "tourliste";
    }

    @GetMapping("/tour/{tnummer}")
    public String getOrtBearbeiten(@PathVariable("tnummer") long tnummer, Model m) {
        
        if(tnummer == 0) {
            // Neuen Ort anlegen
            logger.info("Neue Tour anlegen");

            TourFormular neuesFormular = new TourFormular();
            Tour neueTour = new Tour();

            m.addAttribute("formular", neuesFormular);
            m.addAttribute("tour", neueTour);

            // logger.info(neuesFormular.toString());
            // logger.info(neueTour.toString());

        }
        
        return "tourbearbeiten";
    }

    @PostMapping("/tour/{tnummer}")
    public String postMethodName(
        @PathVariable("tnummer") long tnummer,
        @SessionAttribute("tour") Tour tour,
        @Valid @ModelAttribute("formular") TourFormular formular, 
        BindingResult formularFehler,
        Model m) 
        {

        if(formularFehler.hasErrors()) {
            return "tourbearbeiten";
        } else {
            formular.toTour(tour);

            try {
                Tour tourNeu = tourService.speichereTour(tour);
                return "tourbearbeiten";
            } catch(Exception e) {
                m.addAttribute("info", e.getMessage());
            }
        }
        
        return "tourliste";
    }
    

}
