package de.hsrm.mi.web.projekt.ui.ort;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrtFormular {
    
    @NotNull @NotEmpty
    private String name;

    private double geobreite;
    private double geolaenge;

    private static final double OFFSET_X = 0.05;
    private static final double OFFSET_Y = 0.065;

    private double minX;
    private double minY;

    private double maxX;
    private double maxY;

    public void toOrt(Ort o) {
        o.setName(name);
        o.setGeobreite(geobreite);
        o.setGeolaenge(geolaenge);
    }

    public void fromOrt(Ort o) {
        name = o.getName();
        geobreite = o.getGeobreite();
        geolaenge = o.getGeolaenge();
        computeBoundingBox();
    }

    public void computeBoundingBox() {
        // 50.0805/8.2480
        minX = geobreite - OFFSET_X;
        minY = geolaenge - OFFSET_Y;
        maxX = geobreite + OFFSET_X;
        maxY = geolaenge + OFFSET_Y;
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

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

}
