package EJB;

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
}
