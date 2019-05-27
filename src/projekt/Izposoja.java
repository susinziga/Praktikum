package projekt;

import java.util.Date;

import javax.jws.Oneway;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Izposoja")
public class Izposoja {

	public int id;
	public boolean stanje;
	public Date datumOd;
	public Date datumDo;
	public Narocilo narocilo;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idIzposoja")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isStanje() {
		return stanje;
	}
	public void setStanje(boolean stanje) {
		this.stanje = stanje;
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
	public Narocilo getNarocilo() {
		return narocilo;
	}
	public void setNarocilo(Narocilo narocilo) {
		this.narocilo = narocilo;
	}
	
	
}
