package ru.javavision.dao;

import com.sun.istack.internal.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        Engine result = new Engine();

        try (Session session = factory.openSession()) {

            session.beginTransaction();

            result = session.get(Engine.class, id);

            session.getTransaction().commit();
        }
        return result;
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

    private final static String GET_ALL = "from Engine";

    @Override
    public List<Engine> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery(GET_ALL, Engine.class).list();
        }
    }
}
