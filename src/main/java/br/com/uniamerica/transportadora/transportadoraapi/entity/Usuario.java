package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_usuarios", schema = "transportadora")
public class Usuario extends AbstractEntity {

    @Getter @Setter
    @Column(name = "perc_ganho", nullable = false, scale = 3, precision = 6)
    private BigDecimal percGanho;

    @Getter @Setter
    @Column(name = "login", length = 25, nullable = false, unique = true)
    private String login;

    @Getter @Setter
    @Column(name = "senha", length = 10,nullable = false)
    private String senha;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Grupo grupo;

    @Getter @Setter
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;

    @Getter @Setter
    @Column(name = "telefone", length = 25, nullable = false, unique = true)
    private String telefone;

    @Getter @Setter
    @Column(name = "data_nascimento", length = 10, nullable = false)
    private LocalDate datanascimento;

    @Getter @Setter
    @Column(name = "endereco", length = 25, nullable = false)
    private String endereco;

    @Getter @Setter
    @Column(name = "observacao", length = 200)
    private String observacao;

}
