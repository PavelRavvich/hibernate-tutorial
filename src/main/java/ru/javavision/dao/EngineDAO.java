package ru.javavision.dao;

import com.sun.istack.internal.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javavision.model.Engine;

import java.util.List;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
public class EngineDAO implements DAO<Engine, String> {

    /**
     * Connection factory to database.
     */
    private final SessionFactory factory;

    public EngineDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Create new engine row in engines table.
     *
     * @param engine fof add.
     */
    @Override
    public void create(@NotNull final Engine engine) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(engine);

            session.getTransaction().commit();
        }
    }

    /**
     * Get engine by model.
     *
     * @param model for select.
     * @return Engine obj with param model.
     */
    @Override
    public Engine read(@NotNull final String model) {
        try (final Session session = factory.openSession()) {

            final Engine result = session.get(Engine.class, model);

            return result != null ? result : new Engine();
        }
    }

    /**
     * Update engine state.
     *
     * @param engine new state.
     */
    @Override
    public void update(@NotNull final Engine engine) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(engine);

            session.getTransaction().commit();
        }
    }

    /**
     * Delete engine from engines table.
     *
     * @param engine for delete.
     */
    @Override
    public void delete(@NotNull final Engine engine) {

        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(engine);

            session.getTransaction().commit();
        }
    }

    @Override
    public List<Engine> getAll() {
        return null;
    }
}
