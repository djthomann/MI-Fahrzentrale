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
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtEvent;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtService;
import de.hsrm.mi.web.projekt.messaging.MessageType;
import de.hsrm.mi.web.projekt.messaging.Operation;
import jakarta.transaction.Transactional;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private FrontendNachrichtService nachrichtService;

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
        boolean isNew = (t.getId() == 0);

        Tour ret = tourRepository.save(t);

        Operation operation = isNew ? Operation.CREATE : Operation.UPDATE;
        nachrichtService.sendEvent(new FrontendNachrichtEvent(MessageType.TOUR, operation, t.getId()));

        return ret;
    }

    @Override
    @Transactional
    public void loescheTourMitId(long id) {
        logger.info("Loesche Tour");
        tourRepository.deleteById(id);
        nachrichtService.sendEvent(new FrontendNachrichtEvent(MessageType.TOUR, Operation.DELETE, id));
    }

}
