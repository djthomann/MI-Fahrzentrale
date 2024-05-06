package de.hsrm.mi.web.projekt.ui.benutzer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class BenutzerFormular {
    
    private String name;
    private String surname;
    private String mail;
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate birthday;

    private Set<String> likes = new HashSet<>();
    private Set<String> dislikes = new HashSet<>();
    
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

    public Set<String> getDislikes() {
        return dislikes;
    }

    public void addLike(String s) {
        likes.add(s);
    }

    public void addDislike(String s) {
        dislikes.add(s);
    }

    public int likeAmount() {
        return likes.size();
    }

    public int dislikeAmount() {
        return dislikes.size();
    }

}
