package com.gastos.exes.service;

public interface SecurityPasswordService {

    String getSalt(int length);
    String generateSecurePassword(String password, String salt);
    boolean verifyUserPassword(String providedPassword,
                               String securedPassword, String salt);
    Boolean addSecurePassword(String password, String salt,Integer codPersona);


}
