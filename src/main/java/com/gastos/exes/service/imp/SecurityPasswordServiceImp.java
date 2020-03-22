package com.gastos.exes.service.imp;

import com.gastos.exes.dto.SecutityPersonasDto;
import com.gastos.exes.entities.SecutityPersonas;
import com.gastos.exes.entities.pk.SecurityPersonasKey;
import com.gastos.exes.repository.SecurityPasswordRepo;
import com.gastos.exes.service.SecurityPasswordService;
import com.gastos.exes.util.Cosntants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

@Service("securityPasswordService")
@Slf4j
public class SecurityPasswordServiceImp implements SecurityPasswordService {

    @Autowired
    @Qualifier("securityPasswordRepo")
    SecurityPasswordRepo securityPasswordRepo;

    private static final Random RANDOM = new SecureRandom();

    @Override
    public String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(Cosntants.ALPHABET.charAt(RANDOM.nextInt(Cosntants.ALPHABET.length())));
        }
        return new String(returnValue);
    }

    private static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, Cosntants.ITERATIONS, Cosntants.KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    @Override
    public Boolean addSecurePassword(String password, String salt,Integer codPersona) {

        SecutityPersonas newUserPassword = new SecutityPersonas();
        SecurityPersonasKey securityPersonasKey = new SecurityPersonasKey();
        newUserPassword.setPassword(password);
        newUserPassword.setSALT(salt);
        securityPersonasKey.setCodPersona(codPersona);
        newUserPassword.setPk(securityPersonasKey);
        try {
            securityPasswordRepo.save(newUserPassword);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public SecutityPersonasDto getDataForAutentication(Integer codPersona) {
        SecutityPersonas dataForDecode = securityPasswordRepo.findByPk_CodPersona(codPersona);
        SecutityPersonasDto result = new SecutityPersonasDto();
        result.setSALT(dataForDecode.getSALT());
        result.setPassword(dataForDecode.getPassword());
        return result;
    }

    @Override
    public String generateSecurePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        returnValue = Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }

    @Override
    public boolean verifyUserPassword(String providedPassword,
                                      String securedPassword, String salt)
    {
        boolean returnValue = false;

        // Generate New secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword, salt);
        // Check if two passwords are equal
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

        return returnValue;
    }
}
