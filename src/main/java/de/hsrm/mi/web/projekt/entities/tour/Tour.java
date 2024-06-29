package de.hsrm.mi.web.projekt.entities.tour;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Tour {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private LocalDateTime abfahrDateTime;

    @PositiveOrZero
    private int preis;

    @Positive
    private int plaetze;

    @Size(max = 400)
    private String info;

    @NotNull
    @ManyToOne
    private Benutzer anbieter;

    @NotNull
    @ManyToOne
    private Ort start;

    @NotNull
    @ManyToOne
    private Ort ziel;

    @ManyToMany
    private Set<Benutzer> mitfahrgaeste = new HashSet<>();

    public Set<Benutzer> getMitfahrgaeste() {
        return mitfahrgaeste;
    }

    public Ort getStart() {
        return start;
    }

    public void setStart(Ort start) {
        this.start = start;
    }

    public Ort getZiel() {
        return ziel;
    }

    public void setZiel(Ort ziel) {
        this.ziel = ziel;
    }

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
