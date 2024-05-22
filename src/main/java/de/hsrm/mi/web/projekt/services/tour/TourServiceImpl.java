package de.hsrm.mi.web.projekt.services.tour;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.entities.tour.TourRepository;
import jakarta.transaction.Transactional;

@Service
public class TourServiceImpl implements TourService {
    
    @Autowired private TourRepository tourRepository;

    Logger logger = LoggerFactory.getLogger(TourServiceImpl.class);

    @Override
    @Transactional
    public List<Tour> holeAlleTouren() {
        return tourRepository.findAll(Sort.by("id"));
    }

    @Override
    @Transactional
    public Optional<Tour> holeTourMitId(long id) {
        return tourRepository.findById(id);
    }

    @Override
    @Transactional
    public Tour speichereTour(Tour t) {
        return tourRepository.save(t);
    }

    @Override
    @Transactional
    public void loescheTourMitId(long id) {
        tourRepository.deleteById(id);
    }

}
