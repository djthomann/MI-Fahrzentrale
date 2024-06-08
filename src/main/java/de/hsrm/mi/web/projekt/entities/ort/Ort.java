package de.hsrm.mi.web.projekt.entities.ort;

import de.hsrm.mi.web.projekt.services.geo.GeoAdresse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ort {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotNull @NotEmpty
    private String name;

    private double geobreite;

    private double geolaenge;

    public Ort(long id, String name, double geobreite, double geolaenge) {
        this.id = id;
        this.name = name;
        this.geobreite = geobreite;
        this.geolaenge = geolaenge;
    }

    public Ort() {
        name = " ";
        geobreite = 0;
        geolaenge = 0;
    }

    public static Ort fromRecord(GeoAdresse a) {
        Ort o = new Ort();
        o.setName(a.name());
        o.setGeobreite(a.lat());
        o.setGeolaenge(a.lon());
        return o;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
