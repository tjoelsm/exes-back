package com.gastos.exes.dto;


import com.gastos.exes.util.Cosntants;
import com.gastos.exes.util.NotHtml;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class AutenticateUserRequest {


    @NotNull(message = Cosntants.DATO_OBLIGATORIO)
    @NotHtml
    @Valid
    String email;

    @NotNull(message = Cosntants.DATO_OBLIGATORIO)
    @NotHtml
    @Valid
    String password;

}
