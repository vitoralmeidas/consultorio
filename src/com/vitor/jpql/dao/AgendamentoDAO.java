package com.vitor.jpql.dao;

import javax.persistence.EntityManager;

import com.vitor.jpql.model.Agendamento;

public class AgendamentoDAO extends GenericDAO<Agendamento, Integer> {

	public AgendamentoDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
