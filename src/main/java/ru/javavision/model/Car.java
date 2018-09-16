package ru.javavision.model;

import lombok.*;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Car {

    private int id;

    private String mark;

    private Integer cost;
}
