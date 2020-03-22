package com.gastos.exes.repository;

import com.gastos.exes.entities.SecutityPersonas;
import com.gastos.exes.entities.pk.SecurityPersonasKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("securityPasswordRepo")
@Transactional
public interface SecurityPasswordRepo extends CrudRepository<SecutityPersonas, SecurityPersonasKey> {
}
