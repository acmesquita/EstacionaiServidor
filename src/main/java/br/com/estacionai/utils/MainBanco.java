package br.com.estacionai.utils;

import org.hibernate.Session;

import br.com.estacionai.dao.DAO;

/**
 * @author Catha Mesquita
 *
 */
public class MainBanco {

	public static void main(String[] args) {
		Session session = DAO.beginSession();
		DAO.createDB();
		
		session.close();
	}
}
