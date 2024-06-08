package de.hsrm.mi.web.projekt.api.ort;

import de.hsrm.mi.web.projekt.entities.ort.Ort;

public record OrtDTO(long id, String name, double geobreite, double geolaenge) {
    

    public static OrtDTO fromOrt(Ort o) {
        return new OrtDTO(o.getId(), o.getName(), o.getGeobreite(), o.getGeolaenge());
    }

    public static Ort toOrt(OrtDTO o) {
        return new Ort(o.id(), o.name(), o.geobreite(), o.geolaenge());
    }
}
