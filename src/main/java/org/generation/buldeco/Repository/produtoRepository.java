package org.generation.buldeco.Repository;

import java.util.List;
import org.generation.buldeco.Model.produtoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface produtoRepository extends JpaRepository<produtoModel, Long> {
	public List<produtoModel>findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}

