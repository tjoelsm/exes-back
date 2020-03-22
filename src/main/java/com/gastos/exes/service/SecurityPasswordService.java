package com.gastos.exes.service;

import com.gastos.exes.dto.SecutityPersonasDto;
import com.gastos.exes.entities.SecutityPersonas;

public interface SecurityPasswordService {

    String getSalt(int length);
    String generateSecurePassword(String password, String salt);
    boolean verifyUserPassword(String providedPassword,
                               String securedPassword, String salt);
    Boolean addSecurePassword(String password, String salt,Integer codPersona);
    SecutityPersonasDto getDataForAutentication(Integer codPersona);


}
