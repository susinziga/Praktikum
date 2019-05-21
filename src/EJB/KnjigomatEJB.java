package EJB;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projekt.Knjigomat;
@Stateless
public class KnjigomatEJB implements IKnjigomatEJB{
	@PersistenceContext
	EntityManager em;
	@Override
	public void dodajKnjikomat(Knjigomat k) {
		em.persist(k);
		
	}

	@Override
	public List<Knjigomat> getKnjigomat(int id) {
		return em.createQuery("select a from Knjigomat a where a.idKnjigomat=" + id).getResultList();
		
	}

	@Override
	public List<Knjigomat> vrniVse() {
		return em.createQuery("select a from Knjigomat a " ).getResultList();
		
	}

	@Override
	public Knjigomat najd(int id) {
		
		return em.find(Knjigomat.class, id);
	}

	@Override
	public void update(Knjigomat k) {
		em.merge(k);
		
	}

	@Override
	public void brisi(Knjigomat k) {
		em.remove(em.contains(k) ? k : em.merge(k));
		
	}

}
