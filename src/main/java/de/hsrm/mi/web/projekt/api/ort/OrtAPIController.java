package de.hsrm.mi.web.projekt.api.ort;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class OrtAPIController {
    
    @Autowired OrtService ortService;
    
    @GetMapping(value="/ort", produces=MediaType.APPLICATION_JSON_VALUE)
    public String getAlleOrte() throws JsonProcessingException {
        
        List<Ort> orte = ortService.holeAlleOrte();
        List<OrtDTO> ortDTOs = new ArrayList<>();

        for(Ort o : orte) {
            ortDTOs.add(OrtDTO.fromOrt(o));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        json = objectMapper.writeValueAsString(ortDTOs);

        return json;
    }

    @GetMapping("/ort/{id}")
    public String getOrt(@PathVariable("id") long id) throws ResponseStatusException, JsonProcessingException {
        
        Optional<Ort> ortOptional = ortService.holeOrtMitId(id);

        if(ortOptional.isPresent()) {
            Ort ort = ortOptional.get();
            OrtDTO ortDTO = OrtDTO.fromOrt(ort);
            ObjectMapper objectMapper = new ObjectMapper();
            String json;
            json = objectMapper.writeValueAsString(ortDTO);
            return json;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    
}
