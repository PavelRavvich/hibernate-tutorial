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
//            final Car result = dao.read(1);
//            System.out.println("Read: " + result);
//
//            result.setModel("car_model_03");
//            result.getEngine().setPower(500);
//            dao.update(result);
//            System.out.println("Update: " + dao.read(1));


            System.out.println();
            System.out.println();
            System.out.println();

            //dao.getAll().forEach(System.out::println);

//            final Car car = new Car();
//            car.setModel("test");
//            car.setMark("test");
//            final Engine engine = new Engine();
//            engine.setPower(900);
//            engine.setModel("test");
//            car.setEngine(engine);
//            dao.create(car);
            final Car car = new Car();
            final Engine engine = new Engine();
            engine.setId(3);
            car.setEngine(engine);
            car.setId(3);
            dao.delete(car);
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
