package de.hsrm.mi.web.projekt.services.benutzer;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;
import jakarta.transaction.Transactional;

@Service
public class BenutzerServiceImpl implements BenutzerService {

    @Autowired private BenutzerRepository benutzerRepository;

    Logger logger = LoggerFactory.getLogger(BenutzerServiceImpl.class);

    @Override
    @Transactional
    public List<Benutzer> holeAlleBenutzer() {
        List<Benutzer> benutzer =  benutzerRepository.findAll(Sort.by("name"));

        if(benutzer.isEmpty()) {
            logger.info("No Benutzer found, Database is empty");
        } else {
            logger.info("Retrieved Benutzer from Database" + benutzer.toString());
        }

        return benutzer;
    }

    @Override
    @Transactional
    public Optional<Benutzer> holeBenutzerMitId(long id) {

        Optional<Benutzer> benutzer = benutzerRepository.findById(id);

        if(benutzer.isEmpty()) {
            logger.warn("Could not retrieve Benutzer with id: " + id);
        } else {
            logger.info("Retrieved Benutzer from database: " + benutzer.toString());
        }

        return benutzer;
    }

    @Override
    @Transactional
    public Benutzer speichereBenutzer(Benutzer b) {

        logger.info("Saving Benutzer: " + b.toString());

        Benutzer danach = benutzerRepository.save(b);

        logger.info("Object after saving: " + danach.toString());

        return danach;
    }

    @Override
    @Transactional
    public void loescheBenutzerMitId(long id) {

        logger.info("Deleting Benutzer with id: " + id);

        benutzerRepository.deleteById(id);
    }
    
}
