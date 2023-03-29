package com.vitor.jpql.dao;

import javax.persistence.EntityManager;

import com.vitor.jpql.model.Paciente;

public class PacienteDAO extends GenericDAO<Paciente, Integer>{

	public PacienteDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
}
