package com.gastos.exes.service.imp;

import com.gastos.exes.dto.AutenticateUserRequest;
import com.gastos.exes.dto.SegPersonaDto;
import com.gastos.exes.entities.SegPersonas;
import com.gastos.exes.repository.SegPersonaRepo;
import com.gastos.exes.service.SegPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("segPersonaService")
public class SegPersonaServiceImp implements SegPersonaService {

    @Autowired
    @Qualifier("segPersonaRepo")
    SegPersonaRepo segPersonaRepo;

    @Override
    public Boolean getAutenticationUser(AutenticateUserRequest entrada) {
        SegPersonas resultEntity = segPersonaRepo.findByClave_EmailAndPass(entrada.getEmail(), entrada.getPassword());
        if (resultEntity != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SegPersonaDto getUserData(String email) {
        SegPersonas resultEntity = segPersonaRepo.findByClave_Email(email);
        if (resultEntity != null) {
            SegPersonaDto result = new SegPersonaDto();
            result.setApellidosPersona(resultEntity.getApellidosPersona());
            result.setCodPersona(resultEntity.getClave().getCodPersona());
            result.setNombrePersona(resultEntity.getNombrePersona());
            result.setEmail(resultEntity.getClave().getEmail());
            result.setUltimoAcceso(resultEntity.getUltimoAcceso());
            return result;
        } else {
            return null;
        }

    }

}
