package br.com.estacionai.controller;

import org.hibernate.Session;

import br.com.estacionai.dao.DAO;
import br.com.estacionai.modelo.Estacionamento;
import br.com.estacionai.modelo.Responsavel;

public class EstacionamentoController {

	public static Estacionamento getPorResponsavel(Responsavel responsavel) {
		Session session = DAO.beginSession();
		Estacionamento est = DAO.loadEstacionamento(responsavel);
		session.close();
		return est;
	}

}
