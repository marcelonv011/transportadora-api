package br.com.uniamerica.transportadora.transportadoraapi.services;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Frete;
import br.com.uniamerica.transportadora.transportadoraapi.entity.HistoricoDoFrete;
import br.com.uniamerica.transportadora.transportadoraapi.entity.StatusFrete;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repository.HistoricoDoFreteRepository;
import br.com.uniamerica.transportadora.transportadoraapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class HistoricoDoFreteService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HistoricoDoFreteRepository historicoDoFreteRepository;

    @Transactional
    public void cadastrar(final Frete frete, StatusFrete faturado){

        final Usuario usuario = this.usuarioRepository.findById(1L).orElse(null);

        final HistoricoDoFrete historicoDoFrete = new HistoricoDoFrete();
        historicoDoFrete.setStatusFrete(faturado);
        historicoDoFrete.setData(LocalDateTime.now());
        historicoDoFrete.setFrete(frete);
        historicoDoFrete.setExecutor(usuario);

        this.historicoDoFreteRepository.save(historicoDoFrete);
    }

}
