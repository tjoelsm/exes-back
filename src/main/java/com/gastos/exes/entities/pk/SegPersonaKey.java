package com.gastos.exes.entities.pk;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@Data
public class SegPersonaKey implements Serializable {

    private static final long serialVersionUID=1;

    @Column(name = "TIA01_COD_PERSONA")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codPersona;

    @Column(name = "TIA01_DIRELEC")
    private String email;

}
