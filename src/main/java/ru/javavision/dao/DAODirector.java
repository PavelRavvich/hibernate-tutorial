package ru.javavision.dao;

import com.sun.istack.internal.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.javavision.model.Director;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
public class DAODirector implements DAO<Director, Integer> {
    /**
     * Gate to database.
     */
    private final SessionFactory sessionFactory;

    /**
     * Default constructor.
     *
     * @param sessionFactory connection gate to database.
     */
    public DAODirector(@NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create new director row.
     *
     * @param director for add to table director.
     */
    public void create(@NotNull final Director director) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(director);
            final Transaction transaction = session.getTransaction();
            transaction.commit();
        }
    }

    /**
     * Get director by id.
     *
     * @param id of director for get.
     * @return director with param id.
     */
    public Director read(@NotNull final Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.get(Director.class, id);
        }
    }

    /**
     * Update existed director state by id.
     *
     * @param director new state for director obj.
     */
    public void update(@NotNull final Director director) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(director);
            final Transaction transaction = session.getTransaction();
            transaction.commit();
        }
    }

    /**
     * Delete director.
     *
     * @param director for delete.
     */
    public void delete(@NotNull final Director director) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(director);
            final Transaction transaction = session.getTransaction();
            transaction.commit();
        }
    }
}
