package com.gastos.exes.service.imp;

import com.gastos.exes.dto.AutenticateUserRequest;
import com.gastos.exes.dto.SegPersonaDto;
import com.gastos.exes.dto.SegPersonaNewDto;
import com.gastos.exes.entities.SegPersonas;
import com.gastos.exes.entities.pk.SegPersonaKey;
import com.gastos.exes.repository.SegPersonaRepo;
import com.gastos.exes.service.SecurityPasswordService;
import com.gastos.exes.service.SegPersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("segPersonaService")
@Slf4j
public class SegPersonaServiceImp implements SegPersonaService {

    @Autowired
    @Qualifier("segPersonaRepo")
    SegPersonaRepo segPersonaRepo;

    @Autowired
    @Qualifier("securityPasswordService")
    SecurityPasswordService securityPasswordService;

    @Override
    public Boolean getAutenticationUser(AutenticateUserRequest entrada) {

        SegPersonas resultEntity = segPersonaRepo.findByPk_EmailAndPass(entrada.getEmail(), entrada.getPassword());
        if (resultEntity != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SegPersonaDto getUserData(String email) {
        SegPersonas resultEntity = segPersonaRepo.findByPk_Email(email);
        if (resultEntity != null) {
            SegPersonaDto result = new SegPersonaDto();
            result.setApellidosPersona(resultEntity.getApellidosPersona());
            result.setCodPersona(resultEntity.getPk().getCodPersona());
            result.setNombrePersona(resultEntity.getNombrePersona());
            result.setEmail(resultEntity.getPk().getEmail());
            result.setUltimoAcceso(resultEntity.getUltimoAcceso());
            return result;
        } else {
            return null;
        }

    }

    @Override
    public Boolean newUsuer(SegPersonaNewDto nuevoUsuario) {
            SegPersonas isExist = segPersonaRepo.findByPk_Email(nuevoUsuario.getEmail());
            if (isExist == null) {
                String salt = securityPasswordService.getSalt(nuevoUsuario.getEmail().length());
                String passwordSecure = securityPasswordService.generateSecurePassword(nuevoUsuario.getPassword(), salt);
                SegPersonas nuevaPersona = new SegPersonas();
                nuevaPersona.setApellidosPersona(nuevoUsuario.getApellidosPersona());
                SegPersonaKey personaKey = new SegPersonaKey();
                personaKey.setEmail(nuevoUsuario.getEmail());
                nuevaPersona.setPk(personaKey);
                nuevaPersona.setNombrePersona(nuevoUsuario.getNombrePersona());
                nuevaPersona.setPass(passwordSecure);
                try {
                 segPersonaRepo.save(nuevaPersona);
                    SegPersonas newPersona = segPersonaRepo.findByPk_Email(nuevoUsuario.getEmail());
                    if (newPersona!=null) {
                        try {
                            securityPasswordService.addSecurePassword(passwordSecure, salt, newPersona.getPk().getCodPersona());
                            return true;
                        } catch (Exception e) {
                            segPersonaRepo.delete(newPersona);
                            log.info("#### ERROR: {}", e.getMessage());
                            return false;
                        }
                    } else {
                        log.info("#### ERROR NO HAY REGISTRO {} ####", nuevoUsuario.getEmail());
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            } else {
                log.info("#### PERSONA EXISTE {} ####", nuevoUsuario.getEmail());
                return false;
            }


    }

}
