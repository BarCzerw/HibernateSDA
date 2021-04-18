import entities.Actor;
import entities.Genre;
import entities.IdEntity;
import entities.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repositories.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Hibernate {

    public static void main(String[] args) {
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory())
        {

            prepareDatabase(sessionFactory);
            genreRepoTest(sessionFactory);

        }
    }

    private static void genreRepoTest(SessionFactory sessionFactory) {
        GenreRepository gr = new GenreRepository(sessionFactory);

        gr.add(new Genre("Romantic"));
        gr.add(new Genre("Adventure"));
        gr.add(new Genre("Comedy"));

        System.out.println("\nFind id 1:");
        Optional<Genre> genreById1 = gr.findById(1);
        System.out.println(genreById1.isPresent() ? genreById1 : "Not found");

        System.out.println("\nFind id 7:");
        Optional<Genre> genreById7 = gr.findById(7);
        System.out.println(genreById7.isPresent() ? genreById7 : "Not found");

        System.out.println("\nDelete id 2:");
        gr.deleteById(2);

        System.out.println("\nFind comedy:");
        List<Genre> comedy = gr.findByName("Comedy");
        comedy.forEach(System.out::println);

        System.out.println("\nFind all:");
        gr.findAll().forEach(System.out::println);

    }

    private static void prepareDatabase(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Genre scifi = new Genre("Science-fiction");
            Genre fantasy = new Genre("Fantasy");
            Genre action = new Genre("Action");

            Movie matrix1 = new Movie("The Matrix", 1999, scifi);
            Movie matrix2 = new Movie("The Matrix Reloaded", 2003, scifi);
            Movie lotr1 = new Movie("The Lord of the Rings: The Fellowship of the Ring", 2001, fantasy);
            Movie lotr2 = new Movie("The Lord of the Rings: The Two Towers", 2002, fantasy);
            Movie john = new Movie("John Wick", 2014, action);

            Actor kr = new Actor("Keanu", "Reeves", 1964, Set.of(matrix1, matrix2, john));
            Actor ew = new Actor("Elijah", "Wood", 1981,Set.of(lotr1,lotr2));
            Actor lf = new Actor("Laurence", "Fishburne", 1961,Set.of(matrix1,matrix2));
            Actor gf = new Actor("Gloria", "Foster", 1933,Set.of(matrix2));
            Actor bh = new Actor("Bernard", "Hill", 1944, Set.of(lotr2, john));

            session.persist(kr);
            session.persist(ew);
            session.persist(lf);
            session.persist(gf);
            session.persist(bh);

            transaction.commit();
        }
    }

}
