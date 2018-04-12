package ru.javavision.model;

import lombok.Data;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
@Data
public class Car {

    private int id;

    private String mark;

    private String model;

    private Engine engine;
}
