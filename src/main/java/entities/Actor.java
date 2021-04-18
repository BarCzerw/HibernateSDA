package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor extends IdEntity {

    private String name;
    private String lastName;
    private Integer yearOfBirth;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "actor_movie",joinColumns = @JoinColumn(name = "idactor"), inverseJoinColumns = @JoinColumn(name = "idmovie"))
    private Set<Movie> movies;

    public Actor() {
    }

    public Actor(String name, String lastName, Integer yearOfBirth, Set<Movie> movies) {
        this.name = name;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

}
