package de.hsrm.mi.web.projekt.ui.benutzer;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Iterator;

public class BenutzerFormular {
    
    private String name;
    private String mail;
    private String birthday;

    private List<String> likes = new ArrayList<>();
    private List<String> dislikes = new ArrayList<>();
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Iterator<String> getLikes() {
        return likes.iterator();
    }

    public Iterator<String> getDislikes() {
        return dislikes.iterator();
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
