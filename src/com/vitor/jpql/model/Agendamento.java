package com.vitor.jpql.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tb_agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_agendamento")
	private int id;

	@Column(name = "dt_agendamento", nullable = false)
	private Calendar dataAgendamento;

	@Column(name = "dt_consulta_prevista", nullable = false)
	private Calendar dataConsulta;

	@ManyToOne
	@JoinColumn(name = "id_especialidade")
	Especialidade especialidade;

	@ManyToOne
	@JoinColumn(name = "id_paciente")
	Paciente paciente;

	@OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL)
	List<Consulta> consultas;

	public Agendamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agendamento(int id, Calendar dataAgendamento, Calendar dataConsulta, Especialidade especialidade,
			Paciente paciente, List<Consulta> consultas) {
		super();
		this.id = id;
		this.dataAgendamento = dataAgendamento;
		this.dataConsulta = dataConsulta;
		this.especialidade = especialidade;
		this.paciente = paciente;
		this.consultas = consultas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Calendar dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Calendar getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Calendar dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

}