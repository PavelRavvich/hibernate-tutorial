package ru.javavision.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javavision.model.Car;
import ru.javavision.model.Engine;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EngineDaoTest {

    private final Engine testEngine = new Engine();
    private SessionFactory factory;
    private DAO<Engine, Integer> engineDAO;

    @Before
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
        engineDAO = new EngineDao(factory);
        testEngine.setModel("test");
        testEngine.setPower(1000);
        engineDAO.create(testEngine);
    }

    @After
    public void after() {
        engineDAO.getAll().forEach(car -> engineDAO.delete(car));
        factory.close();
    }

    @Test
    public void whenCreateNewValidEngineThenEngineIsExist() {
        final Engine engine = new Engine();
        engine.setModel("test_model");
        engine.setPower(10000);
        engineDAO.create(engine);
        final Engine result = engineDAO.getAll().stream().filter(e -> e.getModel().equals("test_model")).findFirst().orElse(null);
        assertNotNull(result);
        assertThat(result.getPower(), is(10000));
        assertThat(result.getModel(), is("test_model"));
    }

    @Test
    public void whenGetAllThenReturnAllEngines() {
        assertThat(engineDAO.getAll().stream().filter(engine -> engine.getModel().equals("test")).findFirst().orElse(new Engine())
                .getModel(), is("test"));
    }

    @Test
    public void whenReadEngineWhichExistThenReturnEngine() {
        final int id = engineDAO.getAll().stream().filter(e -> e.getModel().equals("test")).findFirst().orElse(new Engine()).getId();
        assertThat(engineDAO.read(id).getModel(), is("test"));
    }

    @Test
    public void whenDeleteExistEngineThenEngineIsDeleted() {
        final Engine before = engineDAO.getAll().stream().filter(e -> e.getModel().equals("test")).findFirst().orElse(new Engine());
        engineDAO.delete(before);
        final Engine after = engineDAO.getAll().stream().filter(e -> e.getModel().equals("test")).findFirst().orElse(new Engine());
        assertTrue(before.getId() != 0);
        assertTrue(after.getId() == 0);
    }
}