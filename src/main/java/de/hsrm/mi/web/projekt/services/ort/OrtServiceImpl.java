package de.hsrm.mi.web.projekt.services.ort;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.ort.OrtRepository;
import de.hsrm.mi.web.projekt.services.geo.GeoAdresse;
import de.hsrm.mi.web.projekt.services.geo.GeoService;
import jakarta.transaction.Transactional;

@Service
public class OrtServiceImpl implements OrtService {

    @Autowired private OrtRepository ortRepository;
    @Autowired private GeoService geoService;

    Logger logger = LoggerFactory.getLogger(OrtServiceImpl.class);

    @Override
    @Transactional
    public List<Ort> holeAlleOrt() {
        return ortRepository.findAll(Sort.by("name"));
    }

    @Override
    @Transactional
    public Optional<Ort> holeOrtMitId(long id) {
        return ortRepository.findById(id);
    }

    @Override
    @Transactional
    public Ort speichereOrt(Ort o) {
        return ortRepository.save(o);
    }

    @Override
    @Transactional
    public void loescheOrtMitId(long id) {
        ortRepository.deleteById(id);
    }

    @Override
    public List<Ort> findeOrtsvorschlaegeFuerAdresse(String ort) {
        List<GeoAdresse> geoAdressen = geoService.findeAdressen(ort);
        
        // TODO: Convert Record to Entity
        
        return null;
    }
    
}
