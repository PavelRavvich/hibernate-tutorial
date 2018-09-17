package ru.javavision.model;

import lombok.*;

import java.util.Set;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
@NoArgsConstructor
@EqualsAndHashCode(exclude = "engines")
public class Car {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String mark;

    @Getter
    @Setter
    private Integer cost;

    @Getter
    @Setter
    private Set<Engine> engines;
}
