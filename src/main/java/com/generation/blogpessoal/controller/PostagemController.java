package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens") 

@CrossOrigin(allowedHeaders = "*", origins = "*" )
public class PostagemController {
	
	@Autowired 
	private PostagemRepository postagemRepository;  
	
	@GetMapping
	private ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	@GetMapping("/{id}") //busca a postagem do id 1
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta)) // se encontrar chama de resposta de manda em json
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); //se bão encontrar error 404
	}
}
