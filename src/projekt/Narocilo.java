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
	public String qrNarocilo;
	public Date datumOd;
	public Date datumDo;
	public boolean stanje;
	public Uporabnik uporabnik;
	public Knjigomat knjigomat;
	public List<Knjiga> knjigeNarocilo=new ArrayList<Knjiga>();
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idNarocilo")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQrNarocilo() {
		return qrNarocilo;
	}
	public void setQrNarocilo(String qrNarocilo) {
		this.qrNarocilo = qrNarocilo;
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
	public boolean isStanje() {
		return stanje;
	}
	public void setStanje(boolean stanje) {
		this.stanje = stanje;
	}
	
	@OneToOne
	public Uporabnik getUporabnik() {
		return uporabnik;
	}
	public void setUporabnik(Uporabnik uporabnik) {
		this.uporabnik = uporabnik;
	}
	
	@OneToMany
	public List<Knjiga> getKnjigeNarocilo() {
		return knjigeNarocilo;
	}
	
	public void setKnjigeNarocilo(List<Knjiga> knjigeNarocilo) {
		this.knjigeNarocilo = knjigeNarocilo;
	}
	
	@OneToOne
	public Knjigomat getKnjigomat() {
		return knjigomat;
	}
	public void setKnjigomat(Knjigomat knjigomat) {
		this.knjigomat = knjigomat;
	}
	
	
	
	
}
