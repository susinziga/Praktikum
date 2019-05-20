package projekt;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Knjiga")
public class Knjiga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5204134699484276684L;
	
	private int id;
	private String naslov;
	private String avtor;
	private String vrsta;
	private String naslovnica;
	private int qrKoda;
	
	
	public Knjiga() {
		this("", "", "", "", 0);
	}
	
	public Knjiga(String naslov, String avtor, String vrsta, String naslovnica, int qrKoda) {
		super();
		this.naslov = naslov;
		this.avtor = avtor;
		this.vrsta = vrsta;
		this.naslovnica = naslovnica;
		this.qrKoda = qrKoda;
	}
	
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   @Column(name="idKnjiga")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getAvtor() {
		return avtor;
	}

	public void setAvtor(String avtor) {
		this.avtor = avtor;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public String getNaslovnica() {
		return naslovnica;
	}

	public void setNaslovnica(String naslovnica) {
		this.naslovnica = naslovnica;
	}

	public int getQrKoda() {
		return qrKoda;
	}

	public void setQrKoda(int qrKoda) {
		this.qrKoda = qrKoda;
	}
	@Override
	public String toString() {
		return "Kjiga [naslov=" + naslov + ", avtor=" + avtor + ", vrsta=" + vrsta+ "]";
	}
	
}
