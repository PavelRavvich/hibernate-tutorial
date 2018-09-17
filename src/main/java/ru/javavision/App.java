package ru.javavision;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javavision.dao.CarDAO;
import ru.javavision.dao.DAO;
import ru.javavision.dao.EngineDAO;
import ru.javavision.model.Car;
import ru.javavision.model.Engine;

import java.util.HashSet;
import java.util.Set;

/**
 * Author : Pavel Ravvich.
 * Created : 16/09/2018.
 */
public class App {

    public static void main(String[] args) {

        SessionFactory factory = null;

        try {

            factory = new Configuration().configure().buildSessionFactory();

            DAO<Engine, Integer> engineDao = new EngineDAO(factory);
            DAO<Car, Integer> carDao = new CarDAO<>(factory);

            /**
             * Раскоментируя интересующий метод помните что обращение к данным происходит по id.
             * Убедитесь что данные для методов create update и delete существуют.
             */

//            readEngine(engineDao);

            readCar(carDao);

        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    private static void readEngine(DAO<Engine, Integer> engineDao) {
        final Engine result = engineDao.read(1);
        System.out.println("Read: " + result);
    }

    private static void readCar(DAO<Car, Integer> engineDao) {
        final Car result = engineDao.read(1);
        System.out.println("Read: " + result);
    }

}
