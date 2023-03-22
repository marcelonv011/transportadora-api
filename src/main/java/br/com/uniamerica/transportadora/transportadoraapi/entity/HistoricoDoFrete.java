package br.com.uniamerica.transportadora.transportadoraapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_historicosdofrete", schema = "transportadora")
public class HistoricoDoFrete extends AbstractEntity{

    @Getter @Setter
    @Column(name = "data", nullable = false)
    private LocalDateTime data;



    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private StatusFrete statusFrete;

    @Getter @Setter
    @JoinColumn(name = "id_executor", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Usuario executor;

    @Getter @Setter
    @JoinColumn(name = "id_frete", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Frete frete;
}
