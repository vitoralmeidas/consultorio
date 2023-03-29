package com.vitor.jpql.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consulta")
	private int id;

	@Column(name = "dt_consulta", nullable = false)
	Calendar dataConsulta;

	@ManyToOne
	@JoinColumn(name = "id_paciente")
	Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_especialidade")
	Especialidade especialidade;

	@ManyToOne
	@JoinColumn(name = "id_agendamento")
	Agendamento agendamento;

	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulta(int id, Calendar dataConsulta, Paciente paciente, Especialidade especialidade,
			Agendamento agendamento) {
		super();
		this.id = id;
		this.dataConsulta = dataConsulta;
		this.paciente = paciente;
		this.especialidade = especialidade;
		this.agendamento = agendamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Calendar dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

}
