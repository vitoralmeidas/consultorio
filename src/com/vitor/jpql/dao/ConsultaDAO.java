package com.vitor.jpql.dao;

import javax.persistence.EntityManager;

import com.vitor.jpql.model.Consulta;

public class ConsultaDAO extends GenericDAO<Consulta, Integer> {

	public ConsultaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
