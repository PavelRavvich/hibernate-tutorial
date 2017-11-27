package ru.javavision.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import ru.javavision.model.Engine;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EngineDAOTest {

    private SessionFactory factory;

    private DAO<Engine, String> engineDAO;

    private final Engine testEngine = new Engine();

    @Before
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
        engineDAO = new EngineDAO(factory);
        testEngine.setModel("test");
        testEngine.setPower(1);
    }

    @After
    public void after() {
        if (engineDAO.read("test") != null) {
            engineDAO.delete(testEngine);
        }
        factory.close();
    }

    /**
     * @see ru.javavision.dao.EngineDAO#create(Engine).
     * @see ru.javavision.dao.EngineDAO#read(Object).
     */
    @Test
    public void whenCreateEngineThenEngineIsExist() {
        engineDAO.create(testEngine);
        final Engine result = engineDAO.read("test");
        assertThat(testEngine, is(result));
    }

    /**
     * @see ru.javavision.dao.EngineDAO#update(Engine).
     */
    @Test
    public void whenEngineUpdateThenPowerHasNewValue() {
        engineDAO.create(testEngine);
        testEngine.setPower(2);
        engineDAO.update(testEngine);
        final Engine result = engineDAO.read("test");
        assertThat(result.getPower(), is(2));
    }

    /**
     * @see ru.javavision.dao.EngineDAO#delete(Engine).
     */
    @Test
    public void whenEngineDeleteThenEngineNotExist() {
        engineDAO.create(testEngine);
        final Engine before = engineDAO.read("test");
        engineDAO.delete(testEngine);
        final Engine after = engineDAO.read("test");
        assertNotNull(before.getModel());
        assertNull(after.getModel());
    }
}