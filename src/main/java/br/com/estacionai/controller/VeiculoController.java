package br.com.estacionai.controller;

import java.util.List;

import org.hibernate.Session;

import br.com.estacionai.dao.DAO;
import br.com.estacionai.modelo.Veiculo;

public class VeiculoController {

	public static List<Veiculo> getTodosVeiculos() {
		Session session = DAO.beginSession();
		List<Veiculo> veiculos = DAO.listAll(Veiculo.class);
		session.close();
		return veiculos;
	}

	public static Veiculo getVeiculo(Long id) {
		Session session = DAO.beginSession();
		Veiculo veiculo = DAO.load(Veiculo.class, id);
		session.close();
		return veiculo;
	}

	public static Veiculo createdVeiculo(Veiculo veiculo) {
		Session session = DAO.beginSession();
		DAO.save(veiculo);
		veiculo = DAO.load(Veiculo.class, veiculo.getId());
		session.close();

		return veiculo;
	}

	public static Veiculo modifiedVeiculo(Veiculo veiculo) {
		Session session = DAO.beginSession();
		DAO.save(veiculo);
		session.close();

		return veiculo;
	}

	public static void deleteVeiculo(Long id) {
		Session session = DAO.beginSession();
		Veiculo veiculo = DAO.load(Veiculo.class, id);
		DAO.delete(veiculo);
		session.close();
	}


}
