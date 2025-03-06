package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity //cria uma entidade 
@Table (name = "tb_postagens")	
public class Postagem {
	
	
	@Id //chave priaria
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotBlank(message = "O tributo titulo é obrigatório")
	@Size(min = 5, max = 100, message = "O titulo tem que ser maior que 5 e menor que 100")
	private String titulo;
	
	@NotBlank(message = "O tributo titulo é obrigatório")
	@Size(min = 5, max = 100, message = "O titulo tem que ser maior que 5 e menor que 1000")
	private String texto;
	
	@UpdateTimestamp //pega a hora segundo que a msg for postada
	private LocalDateTime data;
	
	@ManyToOne //relacionamento muitos para um
	@JsonIgnoreProperties("postagem")
	private Tema tema; //adicionando o objeto tema (id, descricao)
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	
}
