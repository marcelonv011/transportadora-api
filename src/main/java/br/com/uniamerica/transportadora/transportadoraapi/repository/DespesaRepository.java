package br.com.uniamerica.transportadora.transportadoraapi.repository;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    //@Query("from transportadora.td_despesas WHERE id_frete = id and ativo = true and id_aprovador_usuario is null;")
    public List<Despesa> findByAtivo(@Param("ativo") final boolean ativo);


}
