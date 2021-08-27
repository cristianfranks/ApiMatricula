package com.contoso.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;
import com.contoso.demo.model.*;

import java.util.*;
@Repository
public interface DetalleMatriculaRepository extends JpaRepository<DetalleMatricula, Integer>{
    
    @Query(value = "SELECT o FROM DetalleMatricula o WHERE o.matricula=?1")
    List<DetalleMatricula> findItemsByMatricula(Matricula matricula);
}
