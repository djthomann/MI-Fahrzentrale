package de.hsrm.mi.web.projekt.ui.tour;

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
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerService;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import de.hsrm.mi.web.projekt.services.tour.TourService;
import jakarta.validation.Valid;


@Controller
@SessionAttributes(names = {"tour", "formular"})
public class TourController {
    
    @Autowired TourService tourService; 
    @Autowired BenutzerService benutzerService;
    @Autowired OrtService ortService;

    Logger logger = LoggerFactory.getLogger(TourController.class);

    @ModelAttribute("formular")
    public void initForm(Model m) {
        m.addAttribute("formular", new TourFormular());
    }

    public void holeBenutzer(Model m) {
        List<Benutzer> benutzer = benutzerService.holeAlleBenutzer();
        m.addAttribute("benutzer", benutzer);
    }

    public void holeOrte(Model m) {
        List<Ort> orte = ortService.holeAlleOrte();
        m.addAttribute("orte", orte);
    }

    @GetMapping("/tour")
    public String getTouren(Model m) {

        m.addAttribute("info", null);
        List<Tour> touren = tourService.holeAlleTouren();
        logger.info("Get Touren, Länge: " + touren.size());
        m.addAttribute("touren", touren);

        return "tour/tourliste";
    }

    @GetMapping("/tour/{tnummer}")
    public String getTourBearbeiten(@PathVariable("tnummer") long tnummer, Model m) {

        logger.info("GetMapping: /tour/tnummer = {}", tnummer);

        m.addAttribute("info", null);
        m.addAttribute("tnummer", tnummer);
        holeBenutzer(m);
        holeOrte(m);

        if(tnummer == 0) {
            // Neuen Ort anlegen
            logger.info("Neue Tour anlegen");

            TourFormular neuesFormular = new TourFormular();
            Tour neueTour = new Tour();

            m.addAttribute("formular", neuesFormular);
            m.addAttribute("tour", neueTour);

            // logger.info(neuesFormular.toString());
            // logger.info(neueTour.toString());
            return "tour/tourbearbeiten";

        } else if(tnummer > 0) {
            // Bestehende Tour bearbeiten

            logger.info("Get: Bestehende Tour bearbeiten");

            Optional<Tour> optionalTour = tourService.holeTourMitId(tnummer);
            if(optionalTour.isEmpty()) {
                logger.info("Tour konnte nicht gefunden werden");
                m.addAttribute("info", "Tour konnte nicht gefunden werden");
                return "tour/tourliste";
            } else {
                Tour tour = optionalTour.get();
                TourFormular tourFormular = new TourFormular();
                tourFormular.fromTour(tour);

                m.addAttribute("tour", tour);
                m.addAttribute("formular", tourFormular);

                logger.info("Tour Formular: " + tourFormular.toString());
                return "tour/tourbearbeiten";
            }

        } else {
            m.addAttribute("info", "Invalide Tour ID");
            return "tour/tourbearbeiten";
        }
    }

    @GetMapping("/tour/{tnummer}/del")
    public String deleteTour(@PathVariable("tnummer") long tnummer, Model m) {
        
        logger.info("Trying to delete tour with id: " + tnummer);
        tourService.loescheTourMitId(tnummer);

        return "redirect:/tour";
    }

    @PostMapping("/tour/{tnummer}")
    public String postMethodName(
        @PathVariable("tnummer") long tnummer,
        @SessionAttribute("tour") Tour tour,
        @Valid @ModelAttribute("formular") TourFormular formular, 
        BindingResult formularFehler,
        Model m) 
        {

            logger.info("PostMapping: tour/tnummer");

        if(formularFehler.hasErrors()) {
            return "tour/tourbearbeiten";
        } else {
            formular.toTour(tour);

            try {
                logger.info("Speicher Tour:", tour.toString());
                Tour tourNeu = tourService.speichereTour(tour);
                long tnummerNeu = tourNeu.getId();
                return "redirect:/tour/" + tnummerNeu;
            } catch(Exception e) {
                m.addAttribute("info", e.getMessage());
                logger.error(String.format("Fehler beim Speichern einer Tour %s", tour.toString()), e.getMessage());
                e.printStackTrace();
            }
        }
        
        return "tour/tourliste";
    }
    

}
