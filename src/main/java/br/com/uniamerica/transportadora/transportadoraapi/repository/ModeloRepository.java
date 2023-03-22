package br.com.uniamerica.transportadora.transportadoraapi.repository;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Marca;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {


    public List<Modelo> findByAtivo(@Param("ativo") final boolean ativo);

    @Query("from Modelo modelo where modelo.marca.id = :marcaId")
    public List<Modelo> findByMarca(@Param("marcaId") Long marcaId);

}
