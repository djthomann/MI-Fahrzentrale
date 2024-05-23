package de.hsrm.mi.web.projekt.entities.tour;

import java.time.LocalDateTime;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Tour {
    
    @Id
    @GeneratedValue
    private long id;

    // @GeneratedValue
    // private long version;

    private LocalDateTime abfahrDateTime;

    @PositiveOrZero
    private int preis;

    @Positive
    private int plaetze;

    @Size(max=400)
    private String info;

    @NotNull
    @ManyToOne
    private Benutzer anbieter;

    public Benutzer getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(Benutzer anbieter) {
        this.anbieter = anbieter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "Tour [id=" + id + ", abfahrDateTime=" + abfahrDateTime + ", preis=" + preis + ", plaetze=" + plaetze
                + ", info=" + info + "]";
    }

    

}
