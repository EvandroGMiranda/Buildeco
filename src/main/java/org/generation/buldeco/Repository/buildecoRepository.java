package org.generation.buldeco.Repository;

import java.util.List;
import org.generation.buldeco.Model.buildecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface buildecoRepository extends JpaRepository<buildecoModel, Long> {
	public List<buildecoModel>findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}

