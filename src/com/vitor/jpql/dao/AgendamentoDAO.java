package com.vitor.jpql.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.vitor.jpql.model.Agendamento;
import com.vitor.jpql.model.Especialidade;
import com.vitor.jpql.model.Paciente;

public class AgendamentoDAO extends GenericDAO<Agendamento, Integer> {

	public AgendamentoDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	public int atualizarDataPrevista(Calendar origem, Calendar nova) {
		em.getTransaction().begin();
		int total = em
				.createQuery("update Agendamento a set a.dataConsulta = :nova where a.dataConsulta = :origem")
				.setParameter("nova", nova).setParameter("origem", origem).executeUpdate();
		em.getTransaction().commit();
		return total;
	}

	public static void insert100Agendamentos() {
		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("Consultorio").createEntityManager();
			AgendamentoDAO dao = new AgendamentoDAO(em);

			em.getTransaction().begin();

			int minYear = 2017;
			int maxYear = 2023;

			Random random = new Random();
			int year = random.nextInt(maxYear - minYear + 1) + minYear;

			List<Especialidade> especialidades = em.createQuery("SELECT e FROM Especialidade e", Especialidade.class)
					.getResultList();
			List<Paciente> pacientes = em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();

			for (int i = 0; i < 101; i++) {
				Agendamento agendamento = new Agendamento();

				Calendar dataAgendamento = Calendar.getInstance();
				dataAgendamento.set(Calendar.YEAR, year);
				dataAgendamento.set(Calendar.MONTH, i % 12);
				dataAgendamento.set(Calendar.DAY_OF_MONTH, random.nextInt(30) + 1);

				agendamento.setDataAgendamento(dataAgendamento);
				agendamento.setDataConsulta(dataAgendamento);

				Especialidade especialidade = especialidades.get(random.nextInt(especialidades.size()));
				agendamento.setEspecialidade(especialidade);

				Paciente paciente = pacientes.get(random.nextInt(pacientes.size()));
				agendamento.setPaciente(paciente);

				dao.cadastra(agendamento);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public List<Agendamento> buscaPorData(Calendar data) {
		return em.createQuery("from Agendamento a where a.dataAgendamento =:d", Agendamento.class)
				.setParameter("d", data).getResultList();
	}

}
