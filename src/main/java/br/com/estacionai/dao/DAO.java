package br.com.estacionai.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import br.com.estacionai.modelo.Estacionamento;
import br.com.estacionai.modelo.Estado;
import br.com.estacionai.modelo.Municipio;
import br.com.estacionai.modelo.Responsavel;
import br.com.estacionai.modelo.Vaga;
import br.com.estacionai.modelo.Veiculo;


/**
 * @author erickpassos
 * @change Catharina Mesquira
 */
@SuppressWarnings("deprecation")
public class DAO {

	private static Configuration c;
	private static SessionFactory sf;
	private static Session s;

	static {
		c = new Configuration();
		c.addAnnotatedClass(Veiculo.class);
		c.addAnnotatedClass(Vaga.class);
		c.addAnnotatedClass(Responsavel.class);
		c.addAnnotatedClass(Municipio.class);
		c.addAnnotatedClass(Estado.class);
		c.addAnnotatedClass(Estacionamento.class);
		sf = c.configure().buildSessionFactory();
	}

	public static void updateDB() {
		SchemaUpdate su = new SchemaUpdate(c);
		su.execute(true, true);
	}
	
	public static void createDB() {
		SchemaExport su = new SchemaExport(c);
		su.create(true, true);
	}

	public static void save(Object o) {
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(o);
		s.flush();
		tx.commit();
	}
	
	public static void delete(Object o) {
		Transaction tx = s.beginTransaction();
		s.delete(o);
		s.flush();
		tx.commit();
	}

	public static Session beginSession() {
		s = sf.openSession();
		return s;
	}

	public static void closeSession() {
		s.close();
	}

	public static <T> T load(Class<T> c, Long id) {
		@SuppressWarnings("unchecked")
		T o = (T) s.get(c, id);
		s.flush();
		return o;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> listAll(Class<T> c) {
		List<T> l = null;
		Criteria cr = s.createCriteria(c);
		l = cr.list();
		s.flush();
		return l;
	}

	public static Responsavel autenticacao(String login, String senha){
		Criteria cr = s.createCriteria(Responsavel.class);
		cr.add(Restrictions.eq("login", login));
		cr.add(Restrictions.eq("senha", senha));
		return (Responsavel) cr.uniqueResult();
	}

	public static Estacionamento loadEstacionamento(Responsavel responsavel) {
		Criteria cr = s.createCriteria(Estacionamento.class);
		cr.add(Restrictions.eq("responsavel", responsavel));
		return (Estacionamento) cr.uniqueResult();
	}
}
