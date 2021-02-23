package se.joakimliden.springlab.entities;


import javax.persistence.*;

@Entity
@Table(name="languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String language;

    public Language(long id, String language) {
        this.id = id;
        this.language = language;
    }

    public Language() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
