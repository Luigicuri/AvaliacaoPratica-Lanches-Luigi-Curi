package io.luigicuri.lanches.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.luigicuri.lanches.enums.Ingrediente;
import io.luigicuri.lanches.enums.TipoLanche;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Lanche {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_lanche")
    Long id;
    String cliente;
    TipoLanche nomeLanche;
    List<Ingrediente> adicional;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Double valorTotal;
}