package com.gastos.exes.entities.pk;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@Data
public class SecurityPersonasKey implements Serializable {

    private static final long serialVersionUID=2;

    @Column(name = "COD_PERSONA")
    private Integer codPersona;


}
