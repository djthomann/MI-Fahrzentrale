package de.hsrm.mi.web.projekt.ui.ort;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrtFormular {
    
    @NotNull @NotEmpty
    private String name;

    private double geobreite;
    private double geolaenge;

    public void toOrt(Ort o) {
        o.setName(name);
        o.setGeobreite(geobreite);
        o.setGeolaenge(geolaenge);
    }

    public void fromOrt(Ort o) {
        name = o.getName();
        geobreite = o.getGeobreite();
        geolaenge = o.getGeolaenge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGeobreite() {
        return geobreite;
    }

    public void setGeobreite(double geobreite) {
        this.geobreite = geobreite;
    }

    public double getGeolaenge() {
        return geolaenge;
    }

    public void setGeolaenge(double geolaenge) {
        this.geolaenge = geolaenge;
    }

    

}
