package com.contoso.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;
import com.contoso.demo.model.*;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{
    @Query(value = "SELECT o FROM Matricula o WHERE o.numeroMatricula=?1")
    Optional<Matricula> findByNumero(String numeroMatricula);
}