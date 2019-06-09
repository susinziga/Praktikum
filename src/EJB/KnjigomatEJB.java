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
	
	@SuppressWarnings("unchecked")
	public String getKnjigomatSt() {
		List<Knjigomat>knj= em.createQuery("SELECT k FROM Knjigomat k").getResultList();
		return knj.size()+"";
	}

	
	public Knjigomat getKnjigomat(int id) {
		return (Knjigomat)em.createQuery("select a from Knjigomat a where a.idKnjigomat=" + id).getSingleResult();
		
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


	

}
