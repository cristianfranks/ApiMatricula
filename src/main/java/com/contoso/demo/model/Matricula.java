package com.contoso.demo.model;

import java.util.Date;
import java.math.BigDecimal;
import lombok.*;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroMatricula;
    private String codigoAlumno;
    
   

    @Transient
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "matricula_id")
    private List<DetalleMatricula> detalleMatriculas;
}
