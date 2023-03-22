package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_caminhoes", schema = "transportadora")
public class Caminhao extends AbstractEntity{

    @Getter @Setter
    @Column(name = "placa", length = 10, nullable = false, unique = true)
    private String placa;


    @Getter @Setter
    @JoinColumn(name = "id_modelo", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Modelo modelo;

    @Getter @Setter
    @Column(name = "ano", nullable = false)
    private int ano;

    @Getter @Setter
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Cor cor;

    @Getter @Setter
    @Column(name = "observacao", length = 120)
    private String observacao;

}
