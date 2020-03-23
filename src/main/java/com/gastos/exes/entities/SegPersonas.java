package com.gastos.exes.entities;

import com.gastos.exes.entities.pk.SegPersonaKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TIA_SEG_PERSONAS")
@Data
@EqualsAndHashCode
public class SegPersonas {

    @EmbeddedId
    private SegPersonaKey pk;

    @Column(name = "TIA01_NOMBRE")
    private String nombrePersona;

    @Column(name = "TIA01_APELLIDOS")
    private String apellidosPersona;

    @Column(name = "TIA01_ULTIMO_ACCESS")
    private Date ultimoAcceso;

    @Column(name = "TIA01_LOGIN_PASS")
    private String pass;

}
