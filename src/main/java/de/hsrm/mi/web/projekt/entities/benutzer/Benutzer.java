package de.hsrm.mi.web.projekt.entities.benutzer;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.validators.GutesPasswort;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotNull
    @Size(min = 3, max = 80)
    private String name;

    @NotNull
    @Size(min = 3, max = 80)
    private String surname;

    @NotNull
    @Email
    @Column(unique = true)
    private String mail;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    @Past
    private LocalDate birthday;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> likes = new HashSet<>();

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> dislikes = new HashSet<>();

    @NotNull
    @NotBlank
    private String password;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "anbieter")
    private Collection<Tour> touren;

    @ManyToMany()
    private Set<Tour> gebuchteTouren = new HashSet<>();

    public Set<Tour> getGebuchteTouren() {
        return gebuchteTouren;
    }

    @Override
    public String toString() {
        return "Benutzer [id=" + id + ", name=" + name + ", surname=" + surname + ", mail=" + mail + ", birthday="
                + birthday + ", likes=" + likes + ", dislikes=" + dislikes + ", password=" + password + "]";
    }

    public long getId() {
        return id;
    }

    /*
     * public long getVersion() {
     * return version;
     * }
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<String> getLikes() {
        return likes;
    }

    public void setLikes(Set<String> likes) {
        this.likes = likes;
    }

    public Set<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<String> dislikes) {
        this.dislikes = dislikes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
