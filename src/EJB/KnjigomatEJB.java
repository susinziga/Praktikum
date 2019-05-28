package EJB;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projekt.Knjiga;
import projekt.Knjigomat;
@Stateless
@Local
public class KnjigomatEJB {
	@PersistenceContext
	EntityManager em;
	
	public void dodajKnjikomat(Knjigomat k) {
		em.persist(k);
		
	}

	
	public List<Knjigomat> getKnjigomat(int id) {
		return em.createQuery("select a from Knjigomat a where a.idKnjigomat=" + id).getResultList();
		
	}

	
	public List<Knjigomat> vrniVse() {
		return em.createQuery("select a from Knjigomat a " ).getResultList();
		
	}


	public Knjigomat najd(int id) {
		
		return em.find(Knjigomat.class, id);
	}
	

	public void update(Knjigomat k) {
		em.merge(k);
		
	}


	public void brisi(Knjigomat k) {
		em.remove(em.contains(k) ? k : em.merge(k));
		
	}


	public void dodajKnjigo(int idKnj, Knjiga k) {
		Knjigomat masina = em.find(Knjigomat.class, idKnj);
		List<Knjiga> vseKnjige = masina.getKnjige();
		vseKnjige.add(k);
		em.merge(masina);
		
	}

}
