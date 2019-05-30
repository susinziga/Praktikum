package EJB;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projekt.Knjigomat;
import projekt.Narocilo;
import projekt.Uporabnik;

@Stateless
@Local
public class UporabnikEJB {
	

	
	@PersistenceContext(unitName="Praktikum")
	private EntityManager em;

	
	public void dodajUporabnika(Uporabnik u) {
		em.persist(u);
		
	}

	
	
public Uporabnik najdId(int id) {
		
		return em.find(Uporabnik.class, id);
	}
public List<Uporabnik> vrniVse() {
	return em.createQuery("select a from Uporabnik a ").getResultList();
	
}
public Uporabnik najdi(String qr) {
	int id= Integer.parseInt(qr);
	return (Uporabnik) em.createQuery("select a from Uporabnik a where idUporabnik="+id).getSingleResult();
}

}
