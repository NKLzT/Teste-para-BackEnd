package com.example.demo.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private String prazo;
    private int duracao;
    private Boolean finalizado;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento idDepartamento;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idPessoa")
    private Pessoa idPessoa;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Departamento getidDepartamento() {
		return idDepartamento;
	}

	public void setidDepartamento(Departamento idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Pessoa getidPessoa() {
		return idPessoa;
	}

	public void setidPessoa(Pessoa idPessoa) {
		this.idPessoa = idPessoa;
	}
}