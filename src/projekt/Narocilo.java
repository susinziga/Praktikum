package projekt;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Narocilo")
public class Narocilo {

	
	public int id;
	public boolean stanje;
	
	public Date datumOd;
	public Date datumDo;
	public Uporabnik uporabnik;
	public Knjigomat knjigomat;
	public Knjiga knjiga;
	
	
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idNarocilo")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDatumOd() {
		return datumOd;
	}
	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}
	public Date getDatumDo() {
		return datumDo;
	}
	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}
	
	
	@OneToOne
	public Uporabnik getUporabnik() {
		return uporabnik;
	}
	public void setUporabnik(Uporabnik uporabnik) {
		this.uporabnik = uporabnik;
	}
	
	
	@OneToOne
	public Knjiga getKnjiga() {
		return knjiga;
	}
	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	@OneToOne
	public Knjigomat getKnjigomat() {
		return knjigomat;
	}
	public void setKnjigomat(Knjigomat knjigomat) {
		this.knjigomat = knjigomat;
	}
	
	public boolean getStanje() {
		return stanje;
	}
	public void setStanje(boolean stanje) {
		this.stanje = stanje;
	}
	
	
}
