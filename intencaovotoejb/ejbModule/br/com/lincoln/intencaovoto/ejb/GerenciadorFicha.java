package br.com.lincoln.intencaovoto.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.lincoln.intencaovoto.jpa.Ficha;
import br.com.lincoln.intencaovoto.util.TestConnectionManager;

@Stateless
@LocalBean
public class GerenciadorFicha {

	@PersistenceContext
	private EntityManager em;

	public void addFicha(Ficha ficha) {
		em.persist(ficha);
	}

	public boolean testConnection() {
		return TestConnectionManager.test();
	}

	// public boolean testConnection() {
	// try {
	// System.out.println(qtdFicha());
	// return true;
	// } catch (Exception ex) {
	// return false;
	// }
	// }

	public int qtdFicha() {
		TypedQuery<Ficha> query = em.createNamedQuery("Ficha.obterTodos",
				Ficha.class);
		return query.getResultList().size();
	}

}
