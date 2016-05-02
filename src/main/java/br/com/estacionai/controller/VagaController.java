package br.com.estacionai.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.estacionai.dao.DAO;
import br.com.estacionai.modelo.Vaga;

public class VagaController {

	public static List<Vaga> getTodasVagas() {

		Session session = DAO.beginSession();
		List<Vaga> vagas = DAO.listAll(Vaga.class);
		session.close();
		
		return vagas;
	}

	public static Vaga getVaga(Long id) {
		
		Session session = DAO.beginSession();
		Vaga vaga = DAO.load(Vaga.class, id);
		session.close();
		
		return vaga;

	}

	public static Vaga createdVaga(Vaga vaga) {
		Session session = DAO.beginSession();
		DAO.save(vaga);
		session.close();
		return vaga;
	}

	public static void modifiedVaga(Vaga vaga) {
		Session session = DAO.beginSession();
		DAO.save(vaga);
		session.close();
	}

	public static void deleteVaga(Long id) {
		Session session = DAO.beginSession();
		Vaga vaga = DAO.load(Vaga.class, id);
		DAO.save(vaga);
		session.close();
	}
	
	public static Vaga emitirTicket(Long id){
		Session session = DAO.beginSession();
		Vaga vaga = DAO.load(Vaga.class, id);
		vaga.getVeiculo().setSaida(new Date());
		vaga.calcularValor();
		DAO.save(vaga);
		session.close();
		return vaga;
	}
	

}
