package com.gastos.exes.dto;

import lombok.Data;

@Data
public class SecutityPersonasDto {

    private Integer codPersona;

    private String password;

    private String SALT;
}
