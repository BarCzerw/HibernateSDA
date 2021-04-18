package entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie extends IdEntity {

    private String title;
    private Integer yearOfRelease;
    @ManyToOne(cascade = CascadeType.ALL)
    private Genre genre;
    @ManyToMany(mappedBy = "movies")
    private Set<Actor> actors;

    public Movie() {
    }

    public Movie(String title, Integer yearOfRelease, Genre genre) {
        super();
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public Genre getGenre() {
        return genre;
    }

    public Set<Actor> getActors() {
        return actors;
    }
}
