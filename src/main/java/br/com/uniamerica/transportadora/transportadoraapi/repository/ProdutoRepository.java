package br.com.uniamerica.transportadora.transportadoraapi.repository;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findByAtivo(@Param("ativo") final boolean ativo);

}
