package com.vitor.jpql.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.vitor.jpql.dao.AgendamentoDAO;
import com.vitor.jpql.dao.ConsultaDAO;
import com.vitor.jpql.dao.EspecialidadeDAO;
import com.vitor.jpql.dao.PacienteDAO;
import com.vitor.jpql.model.Agendamento;
import com.vitor.jpql.model.Consulta;
import com.vitor.jpql.model.Especialidade;

public class Teste {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Consultorio");
		EntityManager em = fabrica.createEntityManager();

		PacienteDAO pacienteDAO = new PacienteDAO(em);
		AgendamentoDAO aDAO = new AgendamentoDAO(em);
		ConsultaDAO consultaDAO = new ConsultaDAO(em);
		EspecialidadeDAO espDAO = new EspecialidadeDAO(em);

		// String email = "vitor.oliveira1904@gmail.com";
		// Long paciente = pacienteDAO.qtPaciente();
		// System.out.println(paciente);

		Calendar origem = new GregorianCalendar(2022, Calendar.JANUARY, 23);
		Calendar nova = new GregorianCalendar(2019, Calendar.APRIL, 19);

		int result = aDAO.atualizarDataPrevista(origem, nova);

		System.out.println(result);

		Especialidade esp = espDAO.buscar(1);

		Calendar inicio = new GregorianCalendar(2022, Calendar.JANUARY, 1);
		Calendar fim = new GregorianCalendar(2022, Calendar.MARCH, 31);

		List<Consulta> lista = consultaDAO.buscarPorEspecialidade(esp);


		for (Consulta consulta : lista) {
			System.out.println(consulta.getEspecialidade().getNomeEspecialidade());
			System.out.println("------------------------------------------------------");
		}

		em.close();
		fabrica.close();

	}
}
