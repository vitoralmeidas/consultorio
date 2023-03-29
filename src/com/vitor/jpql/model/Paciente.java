package com.vitor.jpql.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente")
	private int id;

	@Column(name = "nm_paciente", length = 80, nullable = false)
	private String nome;

	@Column(name = "ds_email_paciente", length = 80, nullable = false)
	private String email;

	@Temporal(TemporalType.DATE)
	private Calendar dateNasc;

	@Column(name = "ds_genero_paciente")
	private char genero;

	@OneToMany(mappedBy = "paciente")
	List<Agendamento> agendamentos;

	@OneToMany(mappedBy = "paciente")
	private List<Consulta> consultas;

	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paciente(int id, String nome, String email, Calendar dateNasc, char genero, List<Agendamento> agendamentos,
			List<Consulta> consultas) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dateNasc = dateNasc;
		this.genero = genero;
		this.agendamentos = agendamentos;
		this.consultas = consultas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDateNasc() {
		return dateNasc;
	}

	public void setDateNasc(Calendar dateNasc) {
		this.dateNasc = dateNasc;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

}
