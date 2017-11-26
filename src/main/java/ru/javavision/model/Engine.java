package ru.javavision.model;

import lombok.*;

import java.io.Serializable;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Engine implements Serializable {

    private int id;

    private String model;

    private int power;
}
