package com.impacta.api.livraria.repository;

import com.impacta.api.livraria.repository.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findAllByNumero(Long numero);

}
