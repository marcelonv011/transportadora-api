package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_cidades", schema = "transportadora",
        uniqueConstraints = {
        @UniqueConstraint(
                columnNames = { "nome", "id_estado" }
        )
        }
)
public class Cidade extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @Getter @Setter
    @JoinColumn(name = "id_estado", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Estado estado;


}
