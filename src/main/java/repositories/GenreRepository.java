package repositories;

import entities.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class GenreRepository {

    private final SessionFactory sessionFactory;

    public GenreRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Genre> add(Genre genre) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(genre);
            transaction.commit();
            System.out.println("Genre " + genre.getName() + " added with Id=" + genre.getId());
            return Optional.of(genre);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean deleteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Genre genre = session.find(Genre.class, id);
            session.delete(genre);
            transaction.commit();
            System.out.println("Genre of Id=" + id + " deleted");
            return true;
        }
    }

    public Optional<Genre> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Genre genre = session.find(Genre.class, id);
            return Optional.ofNullable(genre);
        }
    }

    public List<Genre> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Genre> query = session.createQuery("SELECT g FROM Genre g WHERE g.name = :name", Genre.class);
            return query.setParameter("name", name).getResultList();
        }
    }

    public List<Genre> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Genre> query = session.createQuery("SELECT g FROM Genre g", Genre.class);
            return query.getResultList();
        }
    }
}
