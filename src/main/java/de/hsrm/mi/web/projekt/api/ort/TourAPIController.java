package de.hsrm.mi.web.projekt.api.ort;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.hsrm.mi.web.projekt.services.tour.TourDTD;
import de.hsrm.mi.web.projekt.services.tour.TourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class TourAPIController {

    @Autowired
    TourService tourService;

    @GetMapping(value = "/tour", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMethodName() throws JsonProcessingException {
        List<TourDTD> tourDTDs = tourService.holeAlleTouren().stream().map(tour -> TourDTD.fromTour(tour)).toList();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(tourDTDs);

        return json;

    }

}
