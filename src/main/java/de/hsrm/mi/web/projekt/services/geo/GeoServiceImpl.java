package de.hsrm.mi.web.projekt.services.geo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeoServiceImpl implements GeoService {

    Logger logger = LoggerFactory.getLogger(GeoServiceImpl.class);

    // https://nominatim.openstreetmap.org/search?q=karlsruhe&format=json&countrycodes=de
    WebClient webClient = WebClient.create("https://nominatim.openstreetmap.org/search");

    @Override
    public List<GeoAdresse> findeAdressen(String ort) {
        
        if(ort == null) {
            logger.error("null ist kein Ort");
            return Collections.emptyList();
        } else {
            // Alles Records abrufen und der Liste hinzufügen
            // GeoAdresse antwort = webClient.get().uri("?q={ort}&format=json&countrycodes=de", ort).retrieve().bodyToMono(GeoAdresse.class).block();
            
            List<GeoAdresse> antworten = webClient.get()
                .uri("?q={ort}&format=json&countrycodes=de", ort)
                .retrieve()
                .bodyToFlux(GeoAdresse.class) // Flux statt Mono, weil Folge von Antworten erwartet
                .collectList() // aus Folge von Antworten Liste machen
                .block();
            
            logger.info("Retrieved: ", antworten.toString());
            return antworten;
        }

    }
    
}
