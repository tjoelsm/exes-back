package com.gastos.exes.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
@Slf4j
public class SecurityValidation {

    @Value("${headers.autorizado}")
    String usuario;

    public Boolean realizarSeguridad(HttpServletRequest requestHeaders) {
        String usuarioHeaders = requestHeaders.getHeader("Authorization");
        if (usuarioHeaders.equals(usuario)){
            log.info("#### Autorizado ####");
            return true;
        } else {
            log.info("#### NO Autorizado ####");
            return false;
        }
    }
}
