package br.com.uniamerica.transportadora.transportadoraapi.services;

import br.com.uniamerica.transportadora.transportadoraapi.entity.*;
import br.com.uniamerica.transportadora.transportadoraapi.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
public class DespesaService {
    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa findById(Long id) {
        return this.despesaRepository.findById(id).orElse(new Despesa());
    }

    @Transactional
    public void despesaativo(final Long id){
        final Despesa despesa = this.despesaRepository.findById(1L).orElse(null);

        final Despesa despesa1 = new Despesa();
        despesa1.setAtivo(true);



        this.despesaRepository.save(despesa);
    }
}
