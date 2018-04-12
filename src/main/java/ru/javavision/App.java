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

            /**
             * Раскоментируя интересющий метод помните что обращение к данным происходит по id.
             * Убедитесь что данные для методов create update и delete существуют.
             */

//            read(dao);

//            update(dao);

//            create(dao);

//            delete(dao);

        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    private static void read(DAO<Car, Integer> carDao) {
        final Car result = carDao.read(1);
        System.out.println("Read: " + result);
    }

    private static void update(DAO<Car, Integer> carDao) {
        final Car result = carDao.read(1);
        result.setModel("Mazda");
        result.getEngine().setPower(500);
        result.getEngine().setModel("Super engine");
        carDao.update(result);
        System.out.println("Updated: " + carDao.read(1));
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
        System.out.println("Created: " + carDao.read(2));
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
