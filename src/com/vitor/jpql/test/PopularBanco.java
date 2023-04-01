package com.vitor.jpql.test;

import com.vitor.jpql.dao.AgendamentoDAO;
import com.vitor.jpql.dao.ConsultaDAO;
import com.vitor.jpql.dao.EspecialidadeDAO;
import com.vitor.jpql.dao.PacienteDAO;

public class PopularBanco {

	public static void main(String[] args) {
		PacienteDAO.insert100Paciente();
		EspecialidadeDAO.insert100Especialidade();
		AgendamentoDAO.insert100Agendamentos();
		ConsultaDAO.insert100Consultas();
	}

}
