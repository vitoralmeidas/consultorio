package com.vitor.jpql.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_especialidade")
public class Especialidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_especialidade")
	private int id;

	@Column(name = "nm_especialidade", nullable = false)
	private String nomeEspecialidade;

	@OneToMany(mappedBy = "especialidade", cascade = CascadeType.ALL)
	List<Agendamento> agendamento;
	
	@OneToMany(mappedBy = "especialidade", cascade = CascadeType.ALL)
	private
	List<Consulta> consultas;

	public Especialidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Especialidade(int id, String nomeEspecialidade, List<Agendamento> agendamento) {
		super();
		this.id = id;
		this.nomeEspecialidade = nomeEspecialidade;
		this.agendamento = agendamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public List<Agendamento> getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(List<Agendamento> agendamento) {
		this.agendamento = agendamento;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

}
