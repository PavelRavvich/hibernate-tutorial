package ru.javavision.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import ru.javavision.model.Car;
import ru.javavision.model.Engine;

import static java.util.Objects.nonNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarDAOTest {

    private SessionFactory factory;

    private DAO<Car, Integer> carDAO;
    private DAO<Engine, Integer> engineDAO;

    private Car testCar = new Car();
    private Engine testEngine;

    @Before
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
        beforeEngineProcessing();
        beforeCarProcessing();
    }

    private void beforeEngineProcessing() {
        engineDAO = new EngineDao(factory);
        testEngine = engineDAO.getAll().stream().filter(e -> e.getModel().equals("test")).findFirst().orElse(null);
        if (testEngine == null) {
            final Engine engine = new Engine();
            engine.setPower(100);
            engine.setModel("test");
            engineDAO.create(engine);
        }
        testEngine = engineDAO.getAll().stream().filter(e -> e.getModel().equals("test")).findFirst().orElseThrow(IllegalStateException::new);
    }

    private void beforeCarProcessing() {
        carDAO = new CarDAO(factory);
        testCar.setEngine(testEngine);
        testCar.setMark("test");
        testCar.setModel("test");
    }

    @After
    public void after() {
        carDAO.getAll().forEach(car -> carDAO.delete(car));
        engineDAO.getAll().forEach(engine -> engineDAO.delete(engine));
        factory.close();
    }

    @Test
    public void whenCarCreateThenNewCarIsExist() {
        carDAO.create(testCar);

        final Car result = carDAO.getAll().stream().filter(car ->
                car.getMark().equals("test")).findFirst().orElse(new Car());

        assertThat(result, is(testCar));
    }

    @Test
    public void whenReadCarWhichExistThenReturnActualCar() {
        carDAO.create(testCar);
        final Car anElse = carDAO.getAll().stream().filter(car -> car.getMark().equals("test")).findFirst().orElse(null);
        assert anElse != null;
        final Car result = carDAO.read(anElse.getId());
        assertThat(result.getMark(), is("test"));
    }

    @Test
    public void whenUpdateCarThenCarIsUpdated() {
        carDAO.create(testCar);
        final Car addedCar = carDAO.getAll().stream().filter(car ->
                car.getMark().equals("test")).findFirst().orElse(new Car());

        addedCar.setModel("test update");
        carDAO.update(addedCar);

        final Car result = carDAO.getAll().stream().filter(car ->
                car.getModel().equals("test update")).findFirst().orElse(new Car());

        assertThat(result.getModel(), is("test update"));
    }

    @Test
    public void whenDeleteCarWhichExistThenCarIsDeleted() {
        carDAO.create(testCar);
        final Car before = carDAO.getAll().stream().filter(car ->
                car.getModel().equals("test")).findFirst().orElse(new Car());

        carDAO.delete(before);

        final Car after = carDAO.getAll().stream().filter(car ->
                car.getModel().equals("test")).findFirst().orElse(new Car());

        assertThat(before.getMark(), is("test"));
        assertThat(after.getId(), is(0));
    }

}