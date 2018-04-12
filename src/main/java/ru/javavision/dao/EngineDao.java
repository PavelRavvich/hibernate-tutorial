package ru.javavision.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.javavision.model.Engine;

import java.util.List;

/**
 * Author : Pavel Ravvich.
 * Created : 29/11/2017.
 */
public class EngineDao implements DAO<Engine, Integer> {

    private final SessionFactory factory;

    public EngineDao(@NotNull final SessionFactory factory) {
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
    public Engine read(Integer id) {
        Engine result;

        try (Session session = factory.openSession()) {

            session.beginTransaction();

            result = session.get(Engine.class, id);

            session.getTransaction().commit();
        }
        return result != null ? result : new Engine();
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
