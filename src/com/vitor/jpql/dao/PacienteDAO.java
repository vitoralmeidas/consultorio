package com.vitor.jpql.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.vitor.jpql.model.Paciente;

public class PacienteDAO extends GenericDAO<Paciente, Integer> {

	public PacienteDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
	
	public static void insert100Paciente() {

		EntityManager em = null;

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
	
	public long qtPaciente() {
		return em.createQuery("select count(*) as pacientes from Paciente", Long.class ).getSingleResult();
	}
	
	public Paciente buscaPorEmail(String email) {
		return em.createQuery("from Paciente p where p.email = :e", Paciente.class).setParameter("e", email).getSingleResult();
	}

	public List<Paciente> listarPorNome(int inicio, int fim) {
		return em.createQuery("from Paciente p order by nome desc ", Paciente.class).setFirstResult(inicio).setMaxResults(fim).getResultList();
	}
	
	public List<Paciente> buscarPorNome(String nome){
		return em.createQuery("from Paciente p where p.nome like:nomePaciente ", Paciente.class).setParameter("nomePaciente","%" + nome + "%").getResultList();
	}
}
