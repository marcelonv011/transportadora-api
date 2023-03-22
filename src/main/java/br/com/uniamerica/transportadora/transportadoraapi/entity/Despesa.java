package br.com.uniamerica.transportadora.transportadoraapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_despesas", schema = "transportadora")
public class Despesa extends AbstractEntity{

    @Getter @Setter
    @JoinColumn(name = "id_tipo_despesa", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private TipoDespesa tipodespesa;

    @Getter @Setter
    @Column(name = "valor", nullable = false, scale = 3, precision = 6)
    private BigDecimal valor;

    @Getter @Setter
    @JoinColumn(name = "id_motorista", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario motorista;

    @Getter @Setter
    @Column(name = "data")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data;

    @Getter @Setter
    @JoinColumn(name = "id_aprovador_usuario")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Usuario aprovador;

    @Getter @Setter
    @JoinColumn(name = "id_frete", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Frete frete;


}
