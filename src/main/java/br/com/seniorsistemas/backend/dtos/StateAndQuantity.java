package br.com.seniorsistemas.backend.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StateAndQuantity {

    private String state;

    private Integer quantity;

}
