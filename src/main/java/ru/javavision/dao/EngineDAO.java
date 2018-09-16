package ru.javavision.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.javavision.model.Engine;

/**
 * @author Pavel Ravvich.
 */
public class EngineDAO implements DAO<Engine, Integer> {
    /**
     * Connection factory to database.
     */
    private final SessionFactory factory;

    public EngineDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Engine engine) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(engine);

            session.getTransaction().commit();
        }
    }

    @Override
    public Engine read(@NotNull final Integer id) {
        try (final Session session = factory.openSession()) {
            return session.get(Engine.class, id);
        }
    }

    @Override
    public void update(Engine engine) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(engine);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Engine engine) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(engine);

            session.getTransaction().commit();
        }
    }
}
