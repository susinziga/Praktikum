package iskanje;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import projekt.Knjiga;
import projekt.Knjigomat;
@Entity
@Table(name="KnjigomatKnjiga")
public class KnjigomatKnjiga {
	private int id;
	private Knjiga knjiga;
	private Knjigomat masina;
	private boolean jeNoter;
	private boolean jeSposojena;
	
	
	public KnjigomatKnjiga() {
		
	}
	
	

	public KnjigomatKnjiga(Knjiga knjiga, Knjigomat masina, boolean jeNoter, boolean jeSposojena) {
		super();
		this.knjiga = knjiga;
		this.masina = masina;
		this.jeNoter = jeNoter;
		this.jeSposojena = jeSposojena;
	}



	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@OneToOne
public Knjiga getKnjiga() {
	return knjiga;
}
public void setKnjiga(Knjiga knjiga) {
	this.knjiga = knjiga;
}
@OneToOne
public Knjigomat getMasina() {
	return masina;
}
public void setMasina(Knjigomat masina) {
	this.masina = masina;
}
public boolean isJeNoter() {
	return jeNoter;
}
public void setJeNoter(boolean jeNoter) {
	this.jeNoter = jeNoter;
}
public boolean isJeSposojena() {
	return jeSposojena;
}
public void setJeSposojena(boolean jeSposojena) {
	this.jeSposojena = jeSposojena;
}



}