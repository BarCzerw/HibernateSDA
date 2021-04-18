package entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre extends IdEntity{

    private String name;
    @OneToMany(mappedBy = "id", orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<Movie> movies;

    public Genre() {
    }

    public Genre(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "Genre id=" + id + " - " + name;
    }
}
