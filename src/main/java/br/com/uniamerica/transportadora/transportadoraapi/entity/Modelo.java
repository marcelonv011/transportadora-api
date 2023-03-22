package br.com.uniamerica.transportadora.transportadoraapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_modelos", schema = "transportadora")
public class Modelo extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome_modelo", length = 30, nullable = false)
    private String nome;

    @Getter @Setter
    @JoinColumn(name = "id_marca", nullable = false)
    @ManyToOne
    @JsonIgnore
    private Marca marca;


}
