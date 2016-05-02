package br.com.estacionai.controller;

import java.util.List;

import org.hibernate.Session;

import br.com.estacionai.dao.DAO;
import br.com.estacionai.modelo.Responsavel;
import br.com.estacionai.modelo.Usuario;

public class ResponsavelController {

	public static List<Responsavel> getTodosResponsaveis() {
		Session session = DAO.beginSession();
		List<Responsavel> responsaveis = DAO.listAll(Responsavel.class);
		session.close();
		return responsaveis;
	}

	public static Responsavel getResponsavel(Long id) {
		Session session = DAO.beginSession();
		Responsavel responsavel = DAO.load(Responsavel.class,id);
		session.close();
		return responsavel;
	}

	public static Responsavel autentica(Usuario user){
		Session session = DAO.beginSession();
		Responsavel responsavel = DAO.autenticacao(user.getLogin(), user.getSenha());
		session.close();
		return responsavel;
	}

	public static Responsavel createdResponsavel(Responsavel responsavel) {
		Session session = DAO.beginSession();
		DAO.save(responsavel);
		session.close();
		return responsavel;
	}
}
