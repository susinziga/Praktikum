package projekt;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local
public class KnjigaDao {


	Knjiga knjiga = new Knjiga();

	
	@PersistenceContext(unitName="Praktikum")
	private EntityManager em;

	public void addBook(byte[]slika) {
		String ab="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb=new StringBuilder(50);
		for( int i = 0; i < 50; i++ ) 
		      sb.append( ab.charAt( rnd.nextInt(ab.length()) ) );
		String qrk=sb.toString();
		knjiga.setQrKoda(qrk);
		knjiga.setSlika(slika);
		System.out.println(slika);
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
	
	public Knjiga getKnjigaIde(int id) {
		return (Knjiga)em.createQuery("select a from Knjiga a where a.id=" + id).getSingleResult();
	}
	
	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	
	@SuppressWarnings("unchecked")
	public List<Knjiga> getKnjige() {
		return em.createQuery("SELECT k FROM Knjiga k").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public String getKnjigeSt() {
		List<Knjiga>knj= em.createQuery("SELECT k FROM Knjiga k").getResultList();
		return knj.size()+"";
	}
	
	public void deleteKnjiga(int id) {
		System.out.println("Brišem "+knjiga+".");
		em.createQuery("DELETE FROM Knjiga k WHERE k.id="+id+"").executeUpdate();
	}
	
	public void updateKnjiga(String value) {
		System.out.println("Spremenjam "+knjiga+".");
		int id = findKnjigaId(value);
			em.createQuery("UPDATE Knjiga k SET "
				+ "k.naslov ='"+knjiga.getNaslov()+"', "
				+ "k.avtor ='"+knjiga.getAvtor()+"', "
				+ "k.vrsta ='"+knjiga.getVrsta()+"', "
				
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
	
	public Knjiga najdi(int id) {
			
			return em.find(Knjiga.class, id);
		}
	public void posodobi (Knjiga k) {
		em.merge(k);
	}
	public void brisi(Knjiga k) {
		em.remove(em.contains(k) ? k : em.merge(k));
	}


}
