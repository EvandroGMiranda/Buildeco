package org.generation.buldeco.Controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.generation.buldeco.Model.buildecoModel;
import org.generation.buldeco.Repository.buildecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/Postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class buildecoControler {

	@Autowired
	private buildecoRepository repository;

	@GetMapping
	public ResponseEntity<List<buildecoModel>> getall() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{Id}")
	public ResponseEntity<buildecoModel> getByID(@PathVariable Long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<buildecoModel>> getByTitulo(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<buildecoModel> Post(@Valid @RequestBody buildecoModel postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	@PutMapping
	public ResponseEntity<buildecoModel> put(@Valid @RequestBody buildecoModel postagem) {
		return repository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delet(@PathVariable Long id) {
		Optional<buildecoModel> postagem = repository.findById(id);

		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		repository.deleteById(id);
	}

}
