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



}
