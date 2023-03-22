package br.com.uniamerica.transportadora.transportadoraapi.services;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Despesa;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Frete;
import br.com.uniamerica.transportadora.transportadoraapi.entity.StatusFrete;
import br.com.uniamerica.transportadora.transportadoraapi.repository.DespesaRepository;
import br.com.uniamerica.transportadora.transportadoraapi.repository.FreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FreteService {

    @Autowired
    public FreteRepository freteRepository;
    @Autowired
    private DespesaRepository despesaRepository;
    @Autowired
    private HistoricoDoFreteService historicoDoFreteService;

    public Frete findById(Long id) {
        return this.freteRepository.findById(id).orElse(new Frete());
    }

    @Transactional
    public void verificaCadastroFrete(Frete frete) {

        if("".equals(frete.getProduto()) || "".equals(frete.getCidadeOrigem()) || "".equals(frete.getMotorista()) ||
                "".equals(frete.getCaminhao()) || "".equals(frete.getQuilometragemIni()) ||
                "".equals(frete.getQuilometragemFim())|| "".equals(frete.getTotalBrutoRecebidoNota()) ||
                "".equals(frete.getTotalLiquidoRecebido()) ||
                "".equals(frete.getPesoFinal()) || "".equals(frete.getPesoInicial()) ||
                "".equals(frete.getPesoFinalTransportado()) ||
                "".equals(frete.getPrecoTonelada())
        ){
            throw new RuntimeException("Preencha todos os campos!");
        }
        else{
            throw  new RuntimeException("Cadastro realizado com sucesso!");
        }
    }


    @Transactional
    public void verificaUpdateFrete(Long id, Frete frete) {

        if("".equals(frete.getProduto()) || "".equals(frete.getCidadeOrigem()) || "".equals(frete.getMotorista()) ||
                "".equals(frete.getCaminhao()) || "".equals(frete.getQuilometragemIni()) ||
                "".equals(frete.getQuilometragemFim())|| "".equals(frete.getTotalBrutoRecebidoNota()) ||
                "".equals(frete.getTotalLiquidoRecebido()) ||
                "".equals(frete.getPesoFinal()) || "".equals(frete.getPesoInicial()) ||
                "".equals(frete.getPesoFinalTransportado()) ||
                "".equals(frete.getPrecoTonelada())
        ){
            throw new RuntimeException("Preencha todos os campos!");
        }
        else{
            throw  new RuntimeException("Edição realizado com sucesso!");
        }
    }

    @Transactional
    public void atualizarStatusFaturado(final Long id){
        final Frete frete = this.freteRepository.findById(id).orElseThrow();

        Assert.isTrue(frete == null && frete.getId() == null,
                "não foi possível localizar o frete");


//        if(frete == null && frete.getId() == null){
//            throw new RuntimeException("Não foi possivel localizar o frete");
//        }

        Assert.isTrue(!frete.getStatusFrete().equals(StatusFrete.DESCARGA),
                "Não é possivel faturar um frete que não está com o status de descarga");

      //  final List<Despesa> despesas = this.despesaRepository.findByFreteAndAprovadorIsNull(frete.getId());

        //Assert.isTrue(despesas.size() > 0,
         //       "Não é possível faturar um frete com despesas abertas");

        frete.setStatusFrete(StatusFrete.FATURADO);
        this.freteRepository.save(frete);

        this.historicoDoFreteService.cadastrar(frete, StatusFrete.FATURADO);
    }

    @Transactional
    public void atualizarStatusEmTransporte(final Long id){

        final Frete freteCadastrado = this.freteRepository.findById(id).orElseThrow();


        freteCadastrado.setStatusFrete(StatusFrete.EM_TRANSPORTE);
        freteCadastrado.setQuilometragemIni(freteCadastrado.getQuilometragemIni());
        freteCadastrado.setPesoInicial(freteCadastrado.getPesoInicial());
        freteCadastrado.setObservacao(freteCadastrado.getObservacao());

        this.freteRepository.save(freteCadastrado);

        this.historicoDoFreteService.cadastrar(freteCadastrado, StatusFrete.EM_TRANSPORTE);
    }

    @Transactional
    public void atualizarStatusInterrompido(final Long id){
        final Frete freteCadastrado = this.freteRepository.findById(id).orElseThrow();

        Assert.isTrue(freteCadastrado == null && freteCadastrado.getId() == null,
                "não foi possível localizar o frete");

        freteCadastrado.setStatusFrete(StatusFrete.INTERROMPIDO);
        freteCadastrado.setQuilometragemIni(freteCadastrado.getQuilometragemIni());
        freteCadastrado.setPesoInicial(freteCadastrado.getPesoInicial());
        freteCadastrado.setObservacao(freteCadastrado.getObservacao());
        this.freteRepository.save(freteCadastrado);

        this.historicoDoFreteService.cadastrar(freteCadastrado, StatusFrete.INTERROMPIDO);
    }

    @Transactional
    public void atualizarStatusCancelado(final Long id){
        final Frete freteCadastrado = this.freteRepository.findById(id).orElseThrow();

        Assert.isTrue(freteCadastrado == null && freteCadastrado.getId() == null,
                "não foi possível localizar o frete");

        freteCadastrado.setStatusFrete(StatusFrete.CANCELADO);
        freteCadastrado.setQuilometragemIni(freteCadastrado.getQuilometragemIni());
        freteCadastrado.setPesoInicial(freteCadastrado.getPesoInicial());
        freteCadastrado.setObservacao(freteCadastrado.getObservacao());
        this.freteRepository.save(freteCadastrado);

        this.historicoDoFreteService.cadastrar(freteCadastrado, StatusFrete.CANCELADO);
    }

    @Transactional
    public void atualizarStatusDescarga(final Long id){
        final Frete freteCadastrado = this.freteRepository.findById(id).orElseThrow();

       // Assert.isTrue(frete == null && frete.getId() == null,
         //       "não foi possível localizar o frete");


        freteCadastrado.setStatusFrete(StatusFrete.DESCARGA);
        freteCadastrado.setQuilometragemIni(freteCadastrado.getQuilometragemIni());
        freteCadastrado.setPesoInicial(freteCadastrado.getPesoInicial());
        freteCadastrado.setObservacao(freteCadastrado.getObservacao());
        this.freteRepository.save(freteCadastrado);

        this.historicoDoFreteService.cadastrar(freteCadastrado, StatusFrete.DESCARGA);

    }
}

