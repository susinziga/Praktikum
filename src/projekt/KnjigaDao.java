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
	
	public void deleteKnjiga(String value) {
		System.out.println("Brišem "+knjiga+".");
		int id = findKnjigaId(value);
		em.createQuery("DELETE FROM Knjiga k WHERE k.id="+id+"").executeUpdate();
	}
	
	public void updateKnjiga(String value) {
		System.out.println("Spremenjam "+knjiga+".");
		int id = findKnjigaId(value);
			em.createQuery("UPDATE Knjiga k SET "
				+ "k.naslov ='"+knjiga.getNaslov()+"', "
				+ "k.avtor ='"+knjiga.getAvtor()+"', "
				+ "k.vrsta ='"+knjiga.getVrsta()+"', "
				+ "k.naslovnica ='"+knjiga.getNaslovnica()+"'"
				+ " WHERE k.id ='"+id+"'").executeUpdate();
	}
	
	public void izberiKnjigo(String knjigaInput){
		if(knjigaInput != null) {
			for(Knjiga book : getKnjige()) {
		        if(book.getId() == findKnjigaId(knjigaInput)) {
		        	knjiga = book;
		        	System.out.println("Profil "+knjiga.getNaslov()+" izbran.");
		        }
			}
		}
		if(knjigaInput.equals("nov")) {
			knjiga = new Knjiga();
		}
	}
	
	public int findKnjigaId(String value) {
		
		int id = 0;
		for(Knjiga book : getKnjige()) {
			if(value.equals(book.getNaslov())) {
				id = book.getId();
			}
		}
		return id;
	}
	
	
}
