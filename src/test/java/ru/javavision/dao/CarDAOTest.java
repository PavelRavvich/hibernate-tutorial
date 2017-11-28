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

    private DAO<Car, Integer> carDAO;

    private final Car testCar = new Car();
    private final Engine testEngine = new Engine();

    @Before
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
        carDAO = new CarDAO(factory);
        testEngine.setModel("test");
        testEngine.setPower(100);
        testCar.setEngine(testEngine);
        testCar.setMark("test");
        testCar.setModel("test");
        carDAO.create(testCar);
    }

    @After
    public void after() {
        carDAO.getAll().forEach(car -> carDAO.delete(car));
        factory.close();
    }

    @Test
    public void whenCarCreateThenNewCarIsExist() {

        final Car result = carDAO.getAll().stream().filter(car ->
                car.getMark().equals("test")).findFirst().orElse(new Car());

        assertThat(result, is(testCar));
    }

    @Test
    public void whenReadCarWhichExistThenReturnActualCar() {
        final Car anElse = carDAO.getAll().stream().filter(car -> car.getId() != 0).findFirst().orElse(null);
        assert anElse != null;
        final Car result = carDAO.read(anElse.getId());
        assertThat(result.getMark(), is("test"));
    }

    @Test
    public void whenUpdateCarThenCarIsUpdated() {
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
        final Car before = carDAO.getAll().stream().filter(car ->
                car.getModel().equals("test")).findFirst().orElse(new Car());

        carDAO.delete(before);

        final Car after = carDAO.getAll().stream().filter(car ->
                car.getModel().equals("test")).findFirst().orElse(new Car());

        assertThat(before.getMark(), is("test"));
        assertThat(after.getId(), is(0));
    }

}