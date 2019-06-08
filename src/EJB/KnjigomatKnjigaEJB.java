package EJB;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import iskanje.KnjigomatKnjiga;
import projekt.Knjiga;
import projekt.Knjigomat;
@Stateless
@Local
public class KnjigomatKnjigaEJB {
	@PersistenceContext
	EntityManager em;
	
	public void dodajKnjikomatKnjiga(KnjigomatKnjiga k) {
		em.persist(k);
		
	}
	

	public KnjigomatKnjiga getKnjigomatKnjiga(int idK) {
		List<KnjigomatKnjiga>knkn=em.createQuery("select a from KnjigomatKnjiga a").getResultList();
		KnjigomatKnjiga k=null;
		for(KnjigomatKnjiga kn:knkn) {
			if(kn.isJeNoter()==true&&kn.getKnjiga().getId()==idK) {
				k=kn;
			}
		}
		return k;
	}
	public List<KnjigomatKnjiga> getKnjigomat(int id) {
		return em.createQuery("select a from Knjigomat a where a.id=" + id).getResultList();
		
	}

	
	public List<KnjigomatKnjiga> vrniVse() {
	
		List<KnjigomatKnjiga>knj=em.createQuery("select k from KnjigomatKnjiga k").getResultList();
		return knj;
	}


	

	public void update(KnjigomatKnjiga k) {
		em.merge(k);
		
	}



		
	}


	
