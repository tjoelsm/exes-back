package com.gastos.exes.entities;

import com.gastos.exes.entities.pk.SecurityPersonasKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SEG_SECURITY_USER")
@Data
@EqualsAndHashCode
public class SecutityPersonas {

    @EmbeddedId
    private SecurityPersonasKey pk;

    @Column(name = "PASS")
    private String password;

    @Column(name = "SALT")
    private String SALT;

    @Column(name = "ULTIMA_MOD")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date ultimoAcceso;


}
