package com.gastos.exes.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class SecurityValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityValidation.class);

    @Value("${headers.autorizado}")
    String usuario;

    public Boolean realizarSeguridad(HttpServletRequest requestHeaders) {
        String usuarioHeaders = requestHeaders.getHeader("Authorization");
        if (usuarioHeaders.equals(usuario)){
            LOGGER.info("#### Autorizado ####");
            return true;
        } else {
            LOGGER.info("#### NO Autorizado ####");
            return false;
        }
    }

}
