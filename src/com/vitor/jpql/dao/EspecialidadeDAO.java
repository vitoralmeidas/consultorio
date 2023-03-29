package com.vitor.jpql.dao;

import javax.persistence.EntityManager;

import com.vitor.jpql.model.Especialidade;

public class EspecialidadeDAO extends GenericDAO<Especialidade, Integer> {

	public EspecialidadeDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
}
