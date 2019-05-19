package projekt;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local
public class KnjigaDao {
	

	private Knjiga knjiga = new Knjiga();
	
	@PersistenceContext(unitName="Praktikum")
	private EntityManager em;

	public void addBook() {
		System.out.println("Dodajam "+knjiga+".");
		em.persist(knjiga);
		knjiga = new Knjiga();
	}
	
	public Knjiga getKnjiga() {
		return knjiga;
	}
	public List<Knjiga> getKnjigaId(int id) {
		return em.createQuery("select a from Knjiga a where a.id=" + id).getResultList();
	}
	
	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	@SuppressWarnings("unchecked")
	public List<Knjiga> getKnjige() {
		return em.createQuery("SELECT k FROM Knjiga k").getResultList();
	}
	
	
}
