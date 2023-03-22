package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_estados", schema = "transportadora",
        uniqueConstraints = {
        @UniqueConstraint(
                columnNames = { "nome" }
        )
})
public class Estado extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", length = 25, nullable = false)
    private String nome;

}
