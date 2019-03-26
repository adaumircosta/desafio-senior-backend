package br.com.seniorsistemas.backend.domains;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[city]")
public class City {

    @Id
    private Long ibge_id;

    private String uf;

    private String name;

    private Boolean capital;

    private String lon;

    private String lat;

    private String no_accents;

    private String alternative_names;

    private String microregion;

    private String mesoregion;

}
