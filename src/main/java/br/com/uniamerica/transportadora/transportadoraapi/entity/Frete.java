package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "td_fretes", schema = "transportadora")
public class Frete extends AbstractEntity {

    @Getter @Setter
    @JoinColumn(name = "produto")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Produto produto;

    @Getter @Setter
    @JoinColumn(name = "cidade_origem")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Cidade cidadeOrigem;

    @Getter @Setter
    @JoinColumn(name = "cidade_destino")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Cidade cidadeDestino;

    @Getter @Setter
    @JoinColumn(name = "id_motorista")
    @ManyToOne
    private Usuario motorista;

    @Getter @Setter
    @JoinColumn(name = "id_caminhao")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Caminhao caminhao;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private StatusFrete statusFrete;

    @Getter @Setter
    @Column(name = "quilometragem_inicial")
    private int quilometragemIni;

    @Getter @Setter
    @Column(name = "quilometragem_fim")
    private int quilometragemFim;

    @Getter @Setter
    @Column(name = "total_bruto_recebido_nota", scale = 3, precision = 6)
    private BigDecimal totalBrutoRecebidoNota;

    @Getter @Setter
    @Column(name = "total_liquido_recebido", scale = 3, precision = 6)
    private BigDecimal totalLiquidoRecebido;

    @Getter @Setter
    @Column(name = "peso_inicial", scale = 3, precision = 6)
    private BigDecimal pesoInicial;

    @Getter @Setter
    @Column(name = "peso_final", scale = 3, precision = 6)
    private BigDecimal pesoFinal;

    @Getter @Setter
    @Column(name = "peso_final_transportado", scale = 3, precision = 6)
    private BigDecimal pesoFinalTransportado;

    @Getter @Setter
    @Column(name = "preco_tonelada",  scale = 3, precision = 6)
    private BigDecimal precoTonelada;

    @Getter @Setter
    @Column(name = "observacao", length = 200)
    private String observacao;

}
