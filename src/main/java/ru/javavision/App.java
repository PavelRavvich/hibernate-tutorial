package ru.javavision;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javavision.dao.CarDAO;
import ru.javavision.dao.DAO;
import ru.javavision.model.Car;
import ru.javavision.model.Engine;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 * <p>
 * App
 */
public class App {

    public static void main(String[] args) {

        SessionFactory factory = null;

        try {

            factory = new Configuration().configure().buildSessionFactory();
            DAO<Car, Integer> dao = new CarDAO(factory);
            final Car result = dao.read(1);
            System.out.println("Read: " + result);

//            result.setModel("Mazda");
//            result.getEngine().setPower(500);
//            result.getEngine().setModel("Super engine");
//            dao.update(result);
//            System.out.println("Update: " + dao.read(1));
//
//            create(dao);
//            System.out.println("Update: " + dao.read(2));


//            delete(dao);

            //dao.getAll().forEach(System.out::println);

        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    private static void create(DAO<Car, Integer> carDao) {
        Car car = new Car();
        car.setModel("new model");
        car.setMark("new mark");
        Engine engine = new Engine();
        engine.setModel("new engine");
        engine.setPower(900);
        car.setEngine(engine);
        carDao.create(car);
    }

    private static void delete(DAO<Car, Integer> carDao) {
        Car car = new Car();
        car.setModel("new model");
        car.setMark("new mark");
        car.setId(2);
        Engine engine = new Engine();
        engine.setModel("new engine");
        engine.setPower(900);
        engine.setId(2);
        car.setEngine(engine);
        carDao.delete(car);
    }
}
