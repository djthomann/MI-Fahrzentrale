package de.hsrm.mi.web.projekt.ui.tour;

import java.time.LocalDateTime;

import de.hsrm.mi.web.projekt.entities.tour.Tour;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class TourFormular {

    @Future
    private LocalDateTime abfahrDateTime;

    @PositiveOrZero
    private int preis;

    @Positive
    private int plaetze;

    @Size(max=400)
    private String info;

    public void toTour(Tour t) {
        t.setAbfahrDateTime(abfahrDateTime);
        t.setPreis(preis);
        t.setPlaetze(plaetze);
        t.setInfo(info);
    }

    public void fromTour(Tour t) {
        abfahrDateTime = t.getAbfahrDateTime();
        preis = t.getPreis();
        plaetze = t.getPlaetze();
        info = t.getInfo();
    }

    public LocalDateTime getAbfahrDateTime() {
        return abfahrDateTime;
    }

    public void setAbfahrDateTime(LocalDateTime abfahrDateTime) {
        this.abfahrDateTime = abfahrDateTime;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getPlaetze() {
        return plaetze;
    }

    public void setPlaetze(int plaetze) {
        this.plaetze = plaetze;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "TourFormular [abfahrDateTime=" + abfahrDateTime + ", preis=" + preis + ", plaetze=" + plaetze
                + ", info=" + info + "]";
    }

    

}
