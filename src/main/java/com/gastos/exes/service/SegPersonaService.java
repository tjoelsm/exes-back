package com.gastos.exes.service;

import com.gastos.exes.dto.AutenticateUserRequest;
import com.gastos.exes.dto.SegPersonaDto;

public interface SegPersonaService {

    Boolean getAutenticationUser(AutenticateUserRequest entrada);
    SegPersonaDto getUserData(String email);

}
