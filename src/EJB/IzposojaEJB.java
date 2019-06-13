package EJB;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projekt.Izposoja;
import projekt.Knjiga;
import projekt.Knjigomat;
@Local
@Stateless
public class IzposojaEJB {
	@PersistenceContext
	EntityManager em;
	
	public void dodajizposoja(Izposoja i) {
		em.persist(i);
		
	}
public Izposoja najd(int id) {
		
		return em.find(Izposoja.class, id);
	}
	
public List<Izposoja> vrniVse() {
	return em.createQuery("select a from Izposoja a " ).getResultList();
	
}
public void update(Izposoja i) {
	em.merge(i);
	
}

public void brisi(Izposoja k) {
	em.remove(em.contains(k) ? k : em.merge(k));
	
}
}
