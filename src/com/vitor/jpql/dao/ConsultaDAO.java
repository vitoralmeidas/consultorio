package com.vitor.jpql.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.vitor.jpql.model.Agendamento;
import com.vitor.jpql.model.Consulta;
import com.vitor.jpql.model.Especialidade;
import com.vitor.jpql.model.Paciente;

public class ConsultaDAO extends GenericDAO<Consulta, Integer> {

	public ConsultaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	public static void insert100Consultas() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("Consultorio").createEntityManager();
			ConsultaDAO dao = new ConsultaDAO(em);
			em.getTransaction().begin();

			int minYear = 2017;
			int maxYear = 2023;

			Random random = new Random();
			int year = random.nextInt(maxYear - minYear + 1) + minYear;

			List<Especialidade> especialidades = em.createQuery("SELECT e FROM Especialidade e", Especialidade.class)
					.getResultList();
			List<Paciente> pacientes = em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
			List<Agendamento> agendamentos = em.createQuery("SELECT a FROM Agendamento a", Agendamento.class)
					.getResultList();

			for (int i = 0; i < 101; i++) {
				Consulta consulta = new Consulta();

				Calendar dataConsulta = Calendar.getInstance();
				dataConsulta.add(Calendar.YEAR, year);
				dataConsulta.add(Calendar.MONTH, i % 12);
				dataConsulta.add(Calendar.DAY_OF_MONTH, random.nextInt(30) + 1);

				consulta.setDataConsulta(dataConsulta);

				Especialidade especialidade = especialidades.get(random.nextInt(especialidades.size()));
				consulta.setEspecialidade(especialidade);

				Paciente paciente = pacientes.get(random.nextInt(especialidades.size()));
				consulta.setPaciente(paciente);

				Agendamento agendamento = agendamentos.get(random.nextInt(agendamentos.size()));
				consulta.setAgendamento(agendamento);
				dao.cadastra(consulta);
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

	public List<Consulta> buscarPorEspecialidade(Especialidade esp) {
		return em.createQuery("from Consulta c where c.especialidade =:es", Consulta.class).setParameter("es", esp)
				.getResultList();
	}

	public List<Consulta> buscaPorIntervaloDatas(Calendar inicio, Calendar fim) {
		return em.createQuery("from Consulta c where c.dataConsulta between :i and :f", Consulta.class)
				.setParameter("i", inicio).setParameter("f", fim).getResultList();
	}

	public List<Consulta> buscarPorEmailPaciente(String email) {
		return em.createQuery("from Consulta c where c.paciente.email =:email", Consulta.class)
				.setParameter("email", email).getResultList();
	}
}
