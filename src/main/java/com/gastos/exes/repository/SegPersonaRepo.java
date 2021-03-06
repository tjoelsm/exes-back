package com.gastos.exes.repository;

import com.gastos.exes.entities.SegPersonas;
import com.gastos.exes.entities.pk.SegPersonaKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("segPersonaRepo")
@Transactional
public interface SegPersonaRepo extends CrudRepository<SegPersonas, SegPersonaKey> {

    SegPersonas findByPk_Email(String email);
    SegPersonas findByPk_EmailAndPass(String email, String pass);

}
