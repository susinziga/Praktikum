package EJB;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projekt.Knjigomat;
import projekt.Narocilo;

@Stateless
@Local
public class NarociloEJB {
	@PersistenceContext
	EntityManager em;
	
	
	public void dodajNarocilo(Narocilo n) {
		em.persist(n);
		
	}
	
	public void update(Narocilo n) {
		em.merge(n);
		
	}
	
	public List<Narocilo> getNarocila() {
		return em.createQuery("select a from Narocilo a ").getResultList();
		
	}
	
public Narocilo najd(int id) {
		
		return em.find(Narocilo.class, id);
	}
}
