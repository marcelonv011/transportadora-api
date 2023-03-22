package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_produtos", schema = "transportadora")
public class Produto extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

}
