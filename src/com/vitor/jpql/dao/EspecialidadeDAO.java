package com.vitor.jpql.dao;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.vitor.jpql.model.Especialidade;

public class EspecialidadeDAO extends GenericDAO<Especialidade, Integer> {

	public EspecialidadeDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	public static void insert100Especialidade() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("Consultorio").createEntityManager();
			EspecialidadeDAO dao = new EspecialidadeDAO(em);

			String[] specialties = { "Clínica Médica", "Pediatria", "Cirurgia Geral", "Ginecologia", "Cardiologia" };

			Random random = new Random();

			em.getTransaction().begin();

			for (int i = 0; i < 101; i++) {
				int idx = random.nextInt(specialties.length - 1) + 0;
				Especialidade especialidade = new Especialidade();
				especialidade.setNomeEspecialidade(specialties[idx]);

				dao.cadastra(especialidade);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
