package ru.javavision.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javavision.model.Car;
import ru.javavision.model.Engine;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarDAOTest {

    private SessionFactory factory;

    private DAO<Car, String> carDAO;

    private DAO<Engine, String> engineDAO;

    private Car testCar = new Car();
    private Engine testEngine = new Engine();

//    @Before
//    public void before() {
//        factory = new Configuration().configure().buildSessionFactory();
//        carDAO = new CarDAO(factory);
//        engineDAO = new EngineDAO(factory);
//        testEngine.setModel("test");
//        testEngine.setPower(1);
//        engineDAO.create(testEngine);
//        testCar.setEngine(testEngine);
//        testCar.setMark("test");
//        testCar.setModel("test");
//        final int engineId = engineDAO.read("test").getId();
//        testCar.setEngineId(engineId);
//    }

    @After
    public void after() {
        if (carDAO.read("test") != null) {
            carDAO.delete(testCar);
        }

        if (engineDAO.read("test") != null) {
            engineDAO.delete(testEngine);
        }

        factory.close();
    }

    @Test
    public void whenCarCreateThenNewCarIsExist() {
        carDAO.create(testCar);
        final Car result = carDAO.read("test");
        assertThat(result, is(testCar));
    }

    @Test
    public void whenThen() {
        carDAO.create(testCar);
        final Car before = carDAO.read("test");
        testCar.setMark("updated");
        carDAO.update(testCar);
        final Car after = carDAO.read("test");
        //assertThat(before.getMark(), is("test"));
        assertThat(after.getMark(), is("updated"));
    }

}