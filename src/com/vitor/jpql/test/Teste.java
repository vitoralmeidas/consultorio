package com.vitor.jpql.test;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.vitor.jpql.dao.AgendamentoDAO;
import com.vitor.jpql.dao.ConsultaDAO;
import com.vitor.jpql.dao.EspecialidadeDAO;
import com.vitor.jpql.dao.PacienteDAO;
import com.vitor.jpql.model.Agendamento;
import com.vitor.jpql.model.Consulta;
import com.vitor.jpql.model.Especialidade;
import com.vitor.jpql.model.Paciente;

public class Teste {
	private static EntityManager em = null;

	public static void insert100Paciente() {

		String[] women = { "Thais", "Sibila", "Natalia", "Anna", "Irani", "Karine", "Mariana" };

		String[] men = { "Joao", "Vitor", "Henrique", "Otavio", "Pedro", "Matheus", "Gustavo" };

		try {
			em = Persistence.createEntityManagerFactory("Consultorio").createEntityManager();
			PacienteDAO dao = new PacienteDAO(em);

			em.getTransaction().begin();

			Random random = new Random();

			for (int i = 0; i < 101; i++) {
				int idx = random.nextInt(women.length - 1) + 0;
				Paciente paciente = new Paciente();
				if (i <= 50) {
					paciente.setNome(women[idx]);
					paciente.setEmail(women[idx] + "@gmail.com");
				} else {
					paciente.setNome(men[idx]);
					paciente.setEmail(men[idx] + "@gmail.com");
				}

				Calendar dataNasc = Calendar.getInstance();
				dataNasc.set(Calendar.YEAR, 1920 + i);
				dataNasc.set(Calendar.MONTH, i % 12);
				dataNasc.set(Calendar.DAY_OF_MONTH, i % 28 + 1);

				paciente.setDateNasc(dataNasc);
				paciente.setGenero(i % 2 == 0 ? 'M' : 'F');

				dao.cadastra(paciente);
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().commit();
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	public static void insert100Especialidade() {
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

	public static void insert100Agendamentos() {

		try {
			em = Persistence.createEntityManagerFactory("Consultorio").createEntityManager();
			AgendamentoDAO dao = new AgendamentoDAO(em);

			em.getTransaction().begin();

			Random random = new Random();
			int year = random.nextInt(2023) + 2018;

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

	public static void insert100Consultas() {
		try {
			em = Persistence.createEntityManagerFactory("Consultorio").createEntityManager();
			ConsultaDAO dao = new ConsultaDAO(em);
			em.getTransaction().begin();

			Random random = new Random();
			int year = random.nextInt(2023) + 2018;

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

	public static void main(String[] args) {
		// insert100Paciente();
		// insert100Especialidade();
		//insert100Agendamentos();
		insert100Consultas();
	}
}
