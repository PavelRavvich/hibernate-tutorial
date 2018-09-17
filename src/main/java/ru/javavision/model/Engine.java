package ru.javavision.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
@NoArgsConstructor
@EqualsAndHashCode(exclude = "cars")
public class Engine {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int power;

    @Getter
    @Setter
    private Set<Car> cars;

}
